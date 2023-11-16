public class Transaction {
    String name;
    boolean isExpense;
    int quantity;
    int unitPrice;
    String month;

    public Transaction(String name, boolean isExpense, int quantity, int unitPrice, String month) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.month = month;

    }

}
