package model;

public class Sale {
    private int saleId;
    private String customerName;
    private double totalAmount;
    private String status; // Pending, Completed, Cancelled

    public Sale(int saleId, String customerName, double totalAmount, String status) {
        setSaleId(saleId);
        setCustomerName(customerName);
        setTotalAmount(totalAmount);
        setStatus(status);
    }

    public Sale() {
        this.saleId = 0;
        this.customerName = "Unknown";
        this.totalAmount = 0.0;
        this.status = "Pending";
    }

    public int getSaleId() { return saleId; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    public void setSaleId(int saleId) {
        if (saleId >= 0) this.saleId = saleId;
        else {
            System.out.println("Warning: saleId cannot be negative! Setting to 0.");
            this.saleId = 0;
        }
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName.trim();
        } else {
            System.out.println("Warning: customerName cannot be empty! Setting to 'Unknown'.");
            this.customerName = "Unknown";
        }
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) this.totalAmount = totalAmount;
        else {
            System.out.println("Warning: totalAmount cannot be negative! Setting to 0.");
            this.totalAmount = 0.0;
        }
    }

    public void setStatus(String status) {
        if (status == null) {
            this.status = "Pending";
            return;
        }
        String s = status.trim();
        if (s.equalsIgnoreCase("Pending") ||
                s.equalsIgnoreCase("Completed") ||
                s.equalsIgnoreCase("Cancelled")) {
            this.status = capitalize(s);
        } else {
            System.out.println("Warning: Invalid status! Setting to 'Pending'.");
            this.status = "Pending";
        }
    }

    private String capitalize(String s) {
        String lower = s.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    public void completeSale() { this.status = "Completed"; }
    public void cancelSale() { this.status = "Cancelled"; }
    public boolean isPending() { return status.equals("Pending"); }

    public void addAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Warning: Amount must be positive!");
            return;
        }
        this.totalAmount += amount;
    }

    @Override
    public String toString() {
        return "Model.Sale{saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
