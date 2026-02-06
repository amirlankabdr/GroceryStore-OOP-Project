package menu;


import database.ProductDAO;
import model.FoodProduct;
import model.NonFoodProduct;
import java.util.Scanner;


public class MenuManager implements Menu {


    private final Scanner scanner = new Scanner(System.in);
    private final ProductDAO dao = new ProductDAO();


    public void displayMenu() {
        System.out.println("\n1. Add Food Product");
        System.out.println("2. Add Non-Food Product");
        System.out.println("3. View Products");
        System.out.println("4. Delete Product");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }


    public void run() {
        while (true) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addFood();
                case 2 -> addNonFood();
                case 3 -> dao.displayAll();
                case 4 -> deleteProduct();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice");
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


    private void deleteProduct() {
        System.out.print("Product ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(dao.deleteProduct(id) ? "Deleted" : "Not found");
    }
}