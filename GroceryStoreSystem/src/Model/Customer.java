package model;

public class Customer {
    private int customerId;
    private String name;
    private String phoneNumber;
    private int loyaltyPoints;

    public Customer(int customerId, String name, String phoneNumber, int loyaltyPoints) {
        setCustomerId(customerId);
        setName(name);
        setPhoneNumber(phoneNumber);
        setLoyaltyPoints(loyaltyPoints);
    }

    public Customer() {
        this.customerId = 0;
        this.name = "Unknown Model.Customer";
        this.phoneNumber = "N/A";
        this.loyaltyPoints = 0;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getLoyaltyPoints() { return loyaltyPoints; }

    public void setCustomerId(int customerId) {
        if (customerId >= 0) {
            this.customerId = customerId;
        } else {
            System.out.println("Warning: customerId cannot be negative! Setting to 0.");
            this.customerId = 0;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            System.out.println("Warning: Model.Customer name cannot be empty! Setting to 'Unknown Model.Customer'.");
            this.name = "Unknown Model.Customer";
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            this.phoneNumber = phoneNumber.trim();
        } else {
            System.out.println("Warning: Phone number cannot be empty! Setting to 'N/A'.");
            this.phoneNumber = "N/A";
        }
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints >= 0) {
            this.loyaltyPoints = loyaltyPoints;
        } else {
            System.out.println("Warning: Loyalty points cannot be negative! Setting to 0.");
            this.loyaltyPoints = 0;
        }
    }

    public void addLoyaltyPoints(int points) {
        if (points <= 0) {
            System.out.println("Warning: Points must be positive!");
            return;
        }
        this.loyaltyPoints += points;
    }

    public boolean isVIP() {
        return loyaltyPoints > 100;
    }

    public String getContactInfo() {
        return name + " (" + phoneNumber + ")";
    }

    @Override
    public String toString() {
        return "Model.Customer{customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}
