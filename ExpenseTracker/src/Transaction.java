public class Transaction {
    private String type; // "income" or "expense"
    private String category;
    private double amount;
    private String date; // Format: YYYY-MM

    public Transaction(String type, String category, double amount, String date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return type + "," + category + "," + amount + "," + date;
    }
}