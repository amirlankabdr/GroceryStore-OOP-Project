import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        addTestData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt();

            switch (choice) {
                case 1: addGeneralProduct(); break;
                case 2: addFoodProduct(); break;
                case 3: addNonFoodProduct(); break;
                case 4: viewAllProducts(); break;
                case 5: demonstrateProductPolymorphism(); break;
                case 6: viewProductsByType(); break;
                case 7: addCustomer(); break;
                case 8: viewAllCustomers(); break;
                case 9: addSale(); break;
                case 10: viewAllSales(); break;
                case 0:
                    System.out.println("\nGoodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice.");
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===============================");
        System.out.println("      GROCERY STORE SYSTEM");
        System.out.println("===============================");
        System.out.println("1. Add Product (General)");
        System.out.println("2. Add Food Product");
        System.out.println("3. Add Non-Food Product");
        System.out.println("4. View All Products (Polymorphic)");
        System.out.println("5. Demonstrate Product Polymorphism");
        System.out.println("6. View Products by Type");
        System.out.println("7. Add Customer");
        System.out.println("8. View All Customers");
        System.out.println("9. Add Sale");
        System.out.println("10. View All Sales");
        System.out.println("0. Exit");
        System.out.println("===============================");
        System.out.print("Enter your choice: ");
    }

    private static void addTestData() {
        products.add(new Product("Rice", 5200, "Grains", true));
        products.add(new FoodProduct("Milk", 650, "Dairy", true, 5));
        products.add(new FoodProduct("Bread", 300, "Bakery", true, 2));
        products.add(new NonFoodProduct("Toaster", 15000, "Appliances", true, 24));

        customers.add(new Customer(5001, "Alice Johnson", "+77011234567", 50));
        customers.add(new Customer(5002, "Bob Williams", "+77012345678", 150));

        sales.add(new Sale(1001, "John Doe", 8000, "Pending"));
        sales.add(new Sale(1002, "Peter Parker", 3500, "Pending"));
    }

    private static void addGeneralProduct() {
        System.out.println("\n--- ADD PRODUCT (GENERAL) ---");

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price (KZT): ");
        double price = readDouble();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Is available? (true/false): ");
        boolean available = readBoolean();

        Product p = new Product(name, price, category, available);
        products.add(p);

        System.out.println("\nProduct added.");
    }

    private static void addFoodProduct() {
        System.out.println("\n--- ADD FOOD PRODUCT ---");

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price (KZT): ");
        double price = readDouble();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Is available? (true/false): ");
        boolean available = readBoolean();

        System.out.print("Enter expiration days: ");
        int days = readInt();

        Product p = new FoodProduct(name, price, category, available, days);
        products.add(p);

        System.out.println("\nFood product added.");
    }

    private static void addNonFoodProduct() {
        System.out.println("\n--- ADD NON-FOOD PRODUCT ---");

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price (KZT): ");
        double price = readDouble();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Is available? (true/false): ");
        boolean available = readBoolean();

        System.out.print("Enter warranty months: ");
        int months = readInt();

        Product p = new NonFoodProduct(name, price, category, available, months);
        products.add(p);

        System.out.println("\nNon-food product added.");
    }

    private static void viewAllProducts() {
        System.out.println("\n===============================");
        System.out.println("      ALL PRODUCTS (LIST)");
        System.out.println("===============================");

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("Total items: " + products.size());
        System.out.println();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + ".");
            p.displayInfo();

            if (p instanceof FoodProduct) {
                FoodProduct fp = (FoodProduct) p;
                if (fp.isExpiringSoon()) System.out.println("   Checked: expiring soon");
            } else if (p instanceof NonFoodProduct) {
                NonFoodProduct np = (NonFoodProduct) p;
                if (np.hasLongWarranty()) System.out.println("   Checked: long warranty");
            }

            System.out.println();
        }
    }

    private static void demonstrateProductPolymorphism() {
        System.out.println("\n===============================");
        System.out.println("   PRODUCT POLYMORPHISM DEMO");
        System.out.println("===============================");

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product p : products) {
            p.displayInfo();
            System.out.println();
        }
    }

    private static void viewProductsByType() {
        System.out.println("\n--- VIEW PRODUCTS BY TYPE ---");
        System.out.println("1. General Products Only");
        System.out.println("2. Food Products Only");
        System.out.println("3. Non-Food Products Only");
        System.out.print("Enter your choice: ");
        int t = readInt();

        int count = 0;
        System.out.println();

        for (Product p : products) {
            boolean show = false;

            if (t == 1) show = !(p instanceof FoodProduct) && !(p instanceof NonFoodProduct);
            else if (t == 2) show = (p instanceof FoodProduct);
            else if (t == 3) show = (p instanceof NonFoodProduct);

            if (show) {
                count++;
                System.out.println(count + ".");
                p.displayInfo();

                if (p instanceof FoodProduct) {
                    FoodProduct fp = (FoodProduct) p;
                    System.out.println("   Expiring Soon: " + (fp.isExpiringSoon() ? "Yes" : "No"));
                } else if (p instanceof NonFoodProduct) {
                    NonFoodProduct np = (NonFoodProduct) p;
                    System.out.println("   Long Warranty: " + (np.hasLongWarranty() ? "Yes" : "No"));
                }

                System.out.println();
            }
        }

        if (count == 0) System.out.println("No products found for this type.");
    }

    private static void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");

        System.out.print("Enter customer ID: ");
        int id = readInt();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter loyalty points: ");
        int points = readInt();

        Customer c = new Customer(id, name, phone, points);
        customers.add(c);

        System.out.println("\nCustomer added.");
    }

    private static void viewAllCustomers() {
        System.out.println("\n===============================");
        System.out.println("         ALL CUSTOMERS");
        System.out.println("===============================");

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("Total customers: " + customers.size());
        System.out.println();

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i + 1) + ". " + c.getName());
            System.out.println("   ID: " + c.getCustomerId());
            System.out.println("   Phone: " + c.getPhoneNumber());
            System.out.println("   Points: " + c.getLoyaltyPoints());
            System.out.println("   VIP: " + (c.isVIP() ? "Yes" : "No"));
            System.out.println();
        }
    }

    private static void addSale() {
        System.out.println("\n--- ADD SALE ---");

        System.out.print("Enter sale ID: ");
        int saleId = readInt();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter total amount (KZT): ");
        double total = readDouble();

        System.out.print("Enter status (Pending/Completed/Cancelled): ");
        String status = scanner.nextLine();

        Sale s = new Sale(saleId, customerName, total, status);
        sales.add(s);

        System.out.println("\nSale added.");
    }

    private static void viewAllSales() {
        System.out.println("\n===============================");
        System.out.println("            ALL SALES");
        System.out.println("===============================");

        if (sales.isEmpty()) {
            System.out.println("No sales found.");
            return;
        }

        System.out.println("Total sales: " + sales.size());
        System.out.println();

        for (int i = 0; i < sales.size(); i++) {
            Sale s = sales.get(i);
            System.out.println((i + 1) + ". Sale ID: " + s.getSaleId());
            System.out.println("   Customer: " + s.getCustomerName());
            System.out.println("   Total: " + String.format("%.2f", s.getTotalAmount()) + " KZT");
            System.out.println("   Status: " + s.getStatus());
            System.out.println();
        }
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid integer: ");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid number: ");
            scanner.nextLine();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private static boolean readBoolean() {
        while (!scanner.hasNextBoolean()) {
            System.out.print("Enter true or false: ");
            scanner.nextLine();
        }
        boolean value = scanner.nextBoolean();
        scanner.nextLine();
        return value;
    }
}
