package menu;

import database.ProductDAO;
import model.FoodProduct;
import model.NonFoodProduct;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductDAO dao = new ProductDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n===== GROCERY STORE MENU =====");
        System.out.println("1. Add Food Product");
        System.out.println("2. Add Non-Food Product");
        System.out.println("3. View All Products");
        System.out.println("4. View Food Products Only");
        System.out.println("5. View Non-Food Products Only");
        System.out.println("6. Update Product");
        System.out.println("7. Delete Product (Safe)");
        System.out.println("8. Search by Name");
        System.out.println("9. Search by Price Range");
        System.out.println("10. Search by Min Price");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        while (true) {
            displayMenu();
            String input = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1 -> addFood();
                case 2 -> addNonFood();
                case 3 -> dao.displayAll();
                case 4 -> dao.displayByType("FOOD");
                case 5 -> dao.displayByType("NONFOOD");
                case 6 -> updateProduct();
                case 7 -> safeDelete();
                case 8 -> searchByName();
                case 9 -> searchByPriceRange();
                case 10 -> searchByMinPrice();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addFood() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Expiration days: ");
        int days = Integer.parseInt(scanner.nextLine());

        dao.insertFood(new FoodProduct(name, price, category, days));
    }

    private void addNonFood() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Warranty months: ");
        int months = Integer.parseInt(scanner.nextLine());

        dao.insertNonFood(new NonFoodProduct(name, price, category, months));
    }

    private void updateProduct() {
        System.out.print("Product ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product existing = dao.getById(id);
        if (existing == null) {
            System.out.println("No product found with ID: " + id);
            return;
        }

        System.out.println("Current: " + existing.getName() + " | " + existing.getPrice() + " | " + existing.getCategory());

        System.out.print("New name (Enter to keep): ");
        String newName = scanner.nextLine();
        if (newName.trim().isEmpty()) newName = existing.getName();

        System.out.print("New price (Enter to keep): ");
        String priceInput = scanner.nextLine();
        double newPrice = priceInput.trim().isEmpty() ? existing.getPrice() : Double.parseDouble(priceInput);

        System.out.print("New category (Enter to keep): ");
        String newCat = scanner.nextLine();
        if (newCat.trim().isEmpty()) newCat = existing.getCategory();

        if (existing instanceof FoodProduct food) {
            System.out.print("New expiration days (Enter to keep " + food.getExpirationDays() + "): ");
            String ex = scanner.nextLine();
            int newDays = ex.trim().isEmpty() ? food.getExpirationDays() : Integer.parseInt(ex);

            boolean ok = dao.updateFood(id, new FoodProduct(newName, newPrice, newCat, newDays));
            System.out.println(ok ? "Updated." : "Update failed.");
        } else if (existing instanceof NonFoodProduct nonFood) {
            System.out.print("New warranty months (Enter to keep " + nonFood.getWarrantyMonths() + "): ");
            String wm = scanner.nextLine();
            int newMonths = wm.trim().isEmpty() ? nonFood.getWarrantyMonths() : Integer.parseInt(wm);

            boolean ok = dao.updateNonFood(id, new NonFoodProduct(newName, newPrice, newCat, newMonths));
            System.out.println(ok ? "Updated." : "Update failed.");
        }
    }

    private void safeDelete() {
        System.out.print("Product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product p = dao.getById(id);
        if (p == null) {
            System.out.println("No product found with ID: " + id);
            return;
        }

        System.out.println("To delete: " + p.getName() + " | " + p.getPrice() + " | " + p.getCategory());
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println(dao.deleteProduct(id) ? "Deleted." : "Delete failed.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void searchByName() {
        System.out.print("Enter name part: ");
        String part = scanner.nextLine();

        List<Product> results = dao.searchByName(part);
        if (results.isEmpty()) {
            System.out.println("No matches.");
            return;
        }
        for (Product p : results) {
            System.out.println(p.getName() + " | " + p.getPrice() + " | " + p.getCategory());
        }
    }

    private void searchByPriceRange() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());

        List<Product> results = dao.searchByPriceRange(min, max);
        for (Product p : results) {
            System.out.println(p.getName() + " | " + p.getPrice() + " | " + p.getCategory());
        }
    }

    private void searchByMinPrice() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());

        List<Product> results = dao.searchByMinPrice(min);
        for (Product p : results) {
            System.out.println(p.getName() + " | " + p.getPrice() + " | " + p.getCategory());
        }
    }
}
