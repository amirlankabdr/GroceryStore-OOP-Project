package database;

import model.FoodProduct;
import model.NonFoodProduct;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void insertFood(FoodProduct p) {
        String sql = "INSERT INTO product(name, price, category, product_type, extra_value) " +
                "VALUES (?, ?, ?, 'FOOD', ?)";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getCategory());
            ps.setInt(4, p.getExpirationDays());
            ps.executeUpdate();
            System.out.println("Food product inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public void insertNonFood(NonFoodProduct p) {
        String sql = "INSERT INTO product(name, price, category, product_type, extra_value) " +
                "VALUES (?, ?, ?, 'NONFOOD', ?)";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getCategory());
            ps.setInt(4, p.getWarrantyMonths());
            ps.executeUpdate();
            System.out.println("Non-food product inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public void displayAll() {
        String sql = "SELECT * FROM product ORDER BY product_id";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return;

        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                printRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public void displayByType(String type) {
        String sql = "SELECT * FROM product WHERE product_type = ? ORDER BY product_id";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, type);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    printRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public Product getById(int id) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return null;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                String type = rs.getString("product_type");
                int extra = rs.getInt("extra_value");

                if ("FOOD".equalsIgnoreCase(type)) {
                    return new FoodProduct(name, price, category, extra);
                } else {
                    return new NonFoodProduct(name, price, category, extra);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public boolean updateFood(int id, FoodProduct p) {
        String sql = "UPDATE product SET name=?, price=?, category=?, product_type='FOOD', extra_value=? " +
                "WHERE product_id = ?";
        return updateCommon(sql, id, p.getName(), p.getPrice(), p.getCategory(), p.getExpirationDays());
    }

    public boolean updateNonFood(int id, NonFoodProduct p) {
        String sql = "UPDATE product SET name=?, price=?, category=?, product_type='NONFOOD', extra_value=? " +
                "WHERE product_id = ?";
        return updateCommon(sql, id, p.getName(), p.getPrice(), p.getCategory(), p.getWarrantyMonths());
    }

    private boolean updateCommon(String sql, int id, String name, double price, String category, int extra) {
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, category);
            ps.setInt(4, extra);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public List<Product> searchByName(String namePart) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + namePart + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rowToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return list;
    }

    public List<Product> searchByPriceRange(double min, double max) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price DESC";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rowToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return list;
    }

    public List<Product> searchByMinPrice(double min) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price >= ? ORDER BY price DESC";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, min);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rowToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return list;
    }

    private Product rowToProduct(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        String category = rs.getString("category");
        String type = rs.getString("product_type");
        int extra = rs.getInt("extra_value");

        if ("FOOD".equalsIgnoreCase(type)) {
            return new FoodProduct(name, price, category, extra);
        }
        return new NonFoodProduct(name, price, category, extra);
    }

    private void printRow(ResultSet rs) throws SQLException {
        System.out.println(
                rs.getInt("product_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("product_type") + " | " +
                        rs.getDouble("price") + " | " +
                        rs.getString("category") + " | extra=" +
                        rs.getInt("extra_value")
        );
    }
}
