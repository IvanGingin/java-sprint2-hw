import java.util.ArrayList;
public class MonthlyReport {
    private ArrayList<Transaction> transactions;

    public MonthlyReport() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void printReport() {
        System.out.println("Месячный отчет:");
        for (Transaction transaction : transactions) {
            String type = transaction.isExpense ? "Расход" : "Доход";
            System.out.println("Товар: " + transaction.itemName + ", Тип: " + type + ", Количество: " + transaction.quantity + ", Цена за единицу: " + transaction.unitPrice);
        }
    }
}
