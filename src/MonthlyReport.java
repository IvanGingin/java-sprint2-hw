import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    private static HashMap<String, ArrayList<Transaction>> monthlyTransactions = new HashMap<>();

    public static void readMonthlyReports() {
        FileReader fileReader = new FileReader();
        String[] fileNames = {"m.202101.csv", "m.202102.csv", "m.202103.csv"};
        monthlyTransactions.clear();
        for (String fileName : fileNames) {
            ArrayList<String> lines = fileReader.readFileContents(fileName);
            String[] fileNameParts = fileName.split("\\.");
            String month = fileNameParts[1];
            ArrayList<Transaction> transactionsForMonth = new ArrayList<>();

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                Transaction transaction = new Transaction(parts[0], Boolean.parseBoolean(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), month);
                transactionsForMonth.add(transaction);
            }

            monthlyTransactions.put(month, transactionsForMonth);
        }
        System.out.println("Месячные отчеты загружены!");
    }

    public static void printMonthlyReports() {
        if (monthlyTransactions.isEmpty()) {
            System.out.println("Месячные отчеты не загружены.");
            return;
        }

        for (String month : monthlyTransactions.keySet()) {
            getMaxProfitOrder(month);
            getMaxSpendOrder(month);

            for (Transaction transaction : monthlyTransactions.get(month)) {
                System.out.print("Товар: " + transaction.name + ", Тип: ");
                if (transaction.isExpense) {
                    System.out.print("Расход");
                } else {
                    System.out.print("Доход");
                }
                System.out.println(", Количество: " + transaction.quantity + ", Цена за единицу: " + transaction.unitPrice);
            }
        }
    }

    private static void getMaxProfitOrder(String month) {
        int maxProfit = 0;
        String maxProfitItem = null;
        for (Transaction transaction : monthlyTransactions.get(month)) {
            if (!transaction.isExpense) {
                int profit = transaction.quantity * transaction.unitPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                    maxProfitItem = transaction.name;
                }
            }
        }
        System.out.println("Месяц " + month + ": Самый прибыльный товар - " + maxProfitItem + " с прибылью " + maxProfit);
    }

    private static void getMaxSpendOrder(String month) {
        int maxSpend = 0;
        String maxSpendItem = null;
        for (Transaction transaction : monthlyTransactions.get(month)) {
            if (transaction.isExpense) {
                int spend = transaction.quantity * transaction.unitPrice;
                if (spend > maxSpend) {
                    maxSpend = spend;
                    maxSpendItem = transaction.name;
                }
            }
        }
        System.out.println("Месяц " + month + ": Самая большая траты - " + maxSpendItem + " с суммой " + maxSpend);
    }
}
