public class Product {
    private String name;
    private double price;
    private String category;
    private boolean available;

    public Product(String name, double price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public Product() {
        this.name = "Unknown Product";
        this.price = 0.0;
        this.category = "General";
        this.available = false;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setAvailable(boolean available) { this.available = available; }

    public void applyDiscount(double percentage) {
        if (percentage < 0) return;
        this.price = this.price * (1 - percentage / 100.0);
    }

    public boolean isExpensive() {
        return this.price > 5000;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price +
                ", category='" + category + "', available=" + available + "}";
    }
}
