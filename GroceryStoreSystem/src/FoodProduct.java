public class FoodProduct extends Product {
    private int expirationDays;

    public FoodProduct(String name, double price, String category, boolean available, int expirationDays) {
        super(name, price, category, available);
        this.expirationDays = Math.max(0, expirationDays);
    }

    public int getExpirationDays() {
        return expirationDays;
    }

    @Override
    public String getProductType() {
        return "Food Product";
    }

    @Override
    public double calculateFinalPrice() {
        if (isExpiringSoon()) return price * 0.80;
        return price;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Expiration: " + expirationDays + " days");
        if (isExpiringSoon()) System.out.println("   Note: Expiring soon (20% discount applied)");
    }

    public boolean isExpiringSoon() {
        return expirationDays <= 3;
    }

    public void reduceDays(int days) {
        if (days > 0) expirationDays = Math.max(0, expirationDays - days);
    }
}
