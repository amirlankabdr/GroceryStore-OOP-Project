public class Main {
    public static void main(String[] args) {
        System.out.println("=== Grocery Store Management System ===");
        System.out.println();

        Product product1 = new Product("Milk", 650.0, "Dairy", true);
        Product product2 = new Product("Rice", 5200.0, "Grains", true);
        Product product3 = new Product();

        Sale sale1 = new Sale(1001, "John Doe", 8000.0, "Pending");
        Sale sale2 = new Sale(1002, "Peter Parker", 0.0, "Pending");

        Customer customer1 = new Customer(5001, "Alice Johnson", "+77011234567", 50);
        Customer customer2 = new Customer(5002, "Bob Williams", "+77012345678", 150);

        System.out.println("--- PRODUCTS ---");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println();

        System.out.println("--- SALES ---");
        System.out.println(sale1);
        System.out.println(sale2);
        System.out.println();

        System.out.println("--- CUSTOMERS ---");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println();

        System.out.println("--- TESTING GETTERS ---");
        System.out.println("Product 1 name: " + product1.getName());
        System.out.println("Sale 1 status: " + sale1.getStatus());
        System.out.println("Customer 1 points: " + customer1.getLoyaltyPoints());
        System.out.println();

        System.out.println("--- TESTING SETTERS ---");
        System.out.println("Updating product3...");
        product3.setName("Apples");
        product3.setPrice(4500.0);
        product3.setCategory("Fruits");
        product3.setAvailable(true);
        System.out.println("Updated: " + product3);
        System.out.println();

        System.out.println("Changing sale2 customer...");
        sale2.setCustomerName("Peter Parker");
        System.out.println("Updated: " + sale2);
        System.out.println();

        System.out.println("--- TESTING PRODUCT METHODS ---");
        System.out.println(product2.getName() + " is expensive: " + product2.isExpensive());
        System.out.println("Applying 10% discount to " + product2.getName());
        product2.applyDiscount(10);
        System.out.println("New price: " + product2.getPrice() + " KZT");
        System.out.println();

        System.out.println("--- TESTING SALE METHODS ---");
        System.out.println("Sale " + sale1.getSaleId() + " pending: " + sale1.isPending());
        sale1.completeSale();
        System.out.println("Sale " + sale1.getSaleId() + " status: " + sale1.getStatus());
        System.out.println();

        System.out.println("Adding to sale " + sale2.getSaleId());
        sale2.addAmount(1500.0);
        sale2.addAmount(2000.0);
        System.out.println("Sale " + sale2.getSaleId() + " total: " + sale2.getTotalAmount() + " KZT");
        System.out.println();

        System.out.println("--- TESTING CUSTOMER METHODS ---");
        System.out.println(customer1.getName() + " is VIP: " + customer1.isVIP());
        System.out.println(customer2.getName() + " is VIP: " + customer2.isVIP());
        System.out.println();

        System.out.println("Adding 60 points to " + customer1.getName());
        customer1.addLoyaltyPoints(60);
        System.out.println(customer1.getName() + " points: " + customer1.getLoyaltyPoints());
        System.out.println(customer1.getName() + " is VIP: " + customer1.isVIP());
        System.out.println();

        System.out.println("--- FINAL STATE ---");
        System.out.println("Products:");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println();

        System.out.println("Sales:");
        System.out.println(sale1);
        System.out.println(sale2);
        System.out.println();

        System.out.println("Customers:");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println();

        System.out.println("=== Program Complete ===");
    }
}
