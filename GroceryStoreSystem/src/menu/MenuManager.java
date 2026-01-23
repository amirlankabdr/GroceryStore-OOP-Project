package menu;

import Model.FoodProduct;
import Model.NonFoodProduct;
import Model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private ArrayList<Product> products = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n1. Add Food Model.Product");
        System.out.println("2. Add Non-Food Model.Product");
        System.out.println("3. View Products");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addFood();
                    case 2 -> addNonFood();
                    case 3 -> viewProducts();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
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

        products.add(new FoodProduct(name, price, category, true, days));
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

        products.add(new NonFoodProduct(name, price, category, true, months));
    }

    private void viewProducts() {
        for (Product p : products) {
            p.displayInfo();
            System.out.println();
        }
    }
}
