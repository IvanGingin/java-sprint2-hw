public class Transaction {
    final String name;
    final boolean isExpense;
    final int quantity;
    final int unitPrice;
    final String month;

    public Transaction(String name, boolean isExpense, int quantity, int unitPrice, String month) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.month = month;

    }

}
