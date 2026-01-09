public class NonFoodProduct extends Product {
    private int warrantyMonths;

    public NonFoodProduct(String name, double price, String category, boolean available, int warrantyMonths) {
        super(name, price, category, available);
        this.warrantyMonths = Math.max(0, warrantyMonths);
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public String getProductType() {
        return "Non-Food Product";
    }

    @Override
    public double calculateFinalPrice() {
        return price * 1.12;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Warranty: " + warrantyMonths + " months");
        if (hasLongWarranty()) System.out.println("   Note: Long warranty");
    }

    public boolean hasLongWarranty() {
        return warrantyMonths >= 24;
    }

    public void extendWarranty(int extraMonths) {
        if (extraMonths > 0) warrantyMonths += extraMonths;
    }
}
