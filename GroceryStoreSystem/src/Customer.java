public class Customer {
    private int customerId;
    private String name;
    private String phoneNumber;
    private int loyaltyPoints;

    public Customer(int customerId, String name, String phoneNumber, int loyaltyPoints) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer() {
        this.customerId = 0;
        this.name = "Unknown Customer";
        this.phoneNumber = "N/A";
        this.loyaltyPoints = 0;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getLoyaltyPoints() { return loyaltyPoints; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }

    public void addLoyaltyPoints(int points) {
        if (points <= 0) return;
        this.loyaltyPoints += points;
    }

    public boolean isVIP() {
        return this.loyaltyPoints > 100;
    }

    public String getContactInfo() {
        return name + " (" + phoneNumber + ")";
    }

    @Override
    public String toString() {
        return "Customer{customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}
