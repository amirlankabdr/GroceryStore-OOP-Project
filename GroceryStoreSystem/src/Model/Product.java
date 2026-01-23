package Model;

public abstract class Product {

    protected String name;
    protected double price;
    protected String category;
    protected boolean available;

    public Product(String name, double price, String category, boolean available) {
        setName(name);
        setPrice(price);
        setCategory(category);
        this.available = available;
    }

    public abstract String getProductType();
    public abstract double calculateFinalPrice();

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Invalid name");
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty())
            throw new IllegalArgumentException("Invalid category");
        this.category = category.trim();
    }

    public void displayInfo() {
        System.out.println("[" + getProductType() + "] " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: " + calculateFinalPrice() + " KZT");
    }
}
