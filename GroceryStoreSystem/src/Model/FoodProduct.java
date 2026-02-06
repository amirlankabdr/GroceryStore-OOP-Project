package model;


public class FoodProduct extends Product {
    private final int expirationDays;


    public FoodProduct(String name, double price, String category, int expirationDays) {
        super(name, price, category);
        this.expirationDays = expirationDays;
    }


    public int getExpirationDays() {
        return expirationDays;
    }
}