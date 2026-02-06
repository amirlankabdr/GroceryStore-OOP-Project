package database;

import model.FoodProduct;
import model.NonFoodProduct;

import java.sql.*;


public class ProductDAO {


    public void insertFood(FoodProduct p) {
        String sql = "INSERT INTO product(name, price, category, product_type, extra_value) VALUES (?, ?, ?, 'FOOD', ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getCategory());
            ps.setInt(4, p.getExpirationDays());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertNonFood(NonFoodProduct p) {
        String sql = "INSERT INTO product(name, price, category, product_type, extra_value) VALUES (?, ?, ?, 'NONFOOD', ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getCategory());
            ps.setInt(4, p.getWarrantyMonths());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void displayAll() {
        String sql = "SELECT * FROM product";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("product_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("product_type") + " | " +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}