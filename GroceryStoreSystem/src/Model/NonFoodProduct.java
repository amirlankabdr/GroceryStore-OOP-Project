package Model;

public class NonFoodProduct extends Product {

    private int warrantyMonths;

    public NonFoodProduct(String name, double price, String category,
                          boolean available, int warrantyMonths) {
        super(name, price, category, available);
        if (warrantyMonths < 0)
            throw new IllegalArgumentException("Invalid warranty");
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getProductType() {
        return "Non-Food Model.Product";
    }

    @Override
    public double calculateFinalPrice() {
        return price * 1.12;
    }
}
