public class Sale {
    private int saleId;
    private String customerName;
    private double totalAmount;
    private String status;

    public Sale(int saleId, String customerName, double totalAmount, String status) {
        this.saleId = saleId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.status = status;
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

    public void setSaleId(int saleId) { this.saleId = saleId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setStatus(String status) { this.status = status; }

    public void completeSale() { this.status = "Completed"; }
    public void cancelSale() { this.status = "Cancelled"; }
    public boolean isPending() { return this.status.equals("Pending"); }

    public void addAmount(double amount) {
        if (amount <= 0) return;
        this.totalAmount += amount;
    }

    @Override
    public String toString() {
        return "Sale{saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
