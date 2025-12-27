public class Product {
    private String name;
    private double price;
    private String category;
    private boolean available;

    public Product(String name, double price, String category, boolean available) {
        setName(name);
        setPrice(price);
        setCategory(category);
        setAvailable(available);
    }

    public Product() {
        this.name = "Unknown Product";
        this.price = 0.0;
        this.category = "General";
        this.available = true;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            System.out.println("Warning: Product name cannot be empty! Setting to 'Unknown Product'.");
            this.name = "Unknown Product";
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Warning: Price cannot be negative! Setting to 0.");
            this.price = 0.0;
        }
    }

    public void setCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            this.category = category.trim();
        } else {
            System.out.println("Warning: Category cannot be empty! Setting to 'General'.");
            this.category = "General";
        }
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void applyDiscount(double percentage) {
        if (percentage <= 0 || percentage > 100) {
            System.out.println("Warning: Invalid discount! Must be (0..100].");
            return;
        }
        this.price = this.price * (1 - percentage / 100.0);
    }

    public boolean isExpensive() {
        return this.price > 5000;
    }

    public String getFormattedPrice() {
        return String.format("%.2f", price);
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price +
                ", category='" + category + "', available=" + available + "}";
    }
}
