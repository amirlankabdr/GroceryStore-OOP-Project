package Model;

public class FoodProduct extends Product implements Discountable {

    private int expirationDays;

    public FoodProduct(String name, double price, String category,
                       boolean available, int expirationDays) {
        super(name, price, category, available);
        if (expirationDays < 0)
            throw new IllegalArgumentException("Invalid expiration days");
        this.expirationDays = expirationDays;
    }

    @Override
    public String getProductType() {
        return "Food Model.Product";
    }

    @Override
    public double calculateFinalPrice() {
        return expirationDays <= 3 ? price * 0.8 : price;
    }

    @Override
    public void applyDiscount(double percent) {
        if (percent <= 0 || percent > 100)
            throw new IllegalArgumentException("Invalid discount");
        price *= (1 - percent / 100);
    }
}
