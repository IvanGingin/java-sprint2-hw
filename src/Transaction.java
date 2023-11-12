public class Transaction {
    String itemName;
    boolean isExpense;
    int quantity;
    int unitPrice;
    public Transaction (String itemName, boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense ;
        this.quantity = quantity;
        this.unitPrice = unitPrice;

    }

}
