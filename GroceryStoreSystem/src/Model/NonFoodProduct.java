package model;


public class NonFoodProduct extends Product {
    private final int warrantyMonths;


    public NonFoodProduct(String name, double price, String category, int warrantyMonths) {
        super(name, price, category);
        this.warrantyMonths = warrantyMonths;
    }


    public int getWarrantyMonths() {
        return warrantyMonths;
    }
}