import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {
    private static FileReader fileReader;
    private static HashMap<Integer, ArrayList<Transaction>> monthlyTransactions;
    static ArrayList<Transaction> monthlyReports;
    static ArrayList<MonthTotalPerYear> yearlyReports;
    public ReportEngine() {
        fileReader = new FileReader();
        monthlyTransactions = new HashMap<>();
        monthlyReports = new ArrayList<>();
        yearlyReports = new ArrayList<>();
    }
    public static void readMonthlyReports() {
        FileReader fileReader = new FileReader();
        for (int month = 1; month <= 3; month++) {
            String fileName = "m.20210" + month + ".csv";
            ArrayList<String> lines = fileReader.readFileContents(fileName);
            ArrayList<Transaction> monthTransactions = new ArrayList<>();

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                Transaction transaction = new Transaction(parts[0], Boolean.parseBoolean(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                monthTransactions.add(transaction);
            }

            monthlyTransactions.put(month, monthTransactions);
        }

        System.out.println("Месячные отчёты загружены!");
    }

    public static void readYearlyReports() {
        FileReader fileReader = new FileReader();
        ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            MonthTotalPerYear item = new MonthTotalPerYear(parts[0], Integer.parseInt(parts[1]), Boolean.parseBoolean(parts[2]));
            yearlyReports.add(item);
        }
    }

    public static void compareReports() {
        if (monthlyTransactions.isEmpty() || yearlyReports.isEmpty()) {
            System.out.println("Необходимо сначала считать все отчеты.");
            return;
        }

        // Логика сравнения отчетов
    }

    public void getMaxProfitOrder(int month) {
        int maxProfit = 0;
        String maxProfitItem = null;
        ArrayList<Transaction> transactions = monthlyTransactions.get(month);
        if (transactions != null) {
            for (Transaction transaction : transactions) {
                if (!transaction.isExpense) {
                    int profit = transaction.quantity * transaction.unitPrice;
                    if (profit > maxProfit) {
                        maxProfit = profit;
                        maxProfitItem = transaction.itemName;
                    }
                }
            }
        }
        System.out.println("Самый прибыльный товар за месяц " + month + ": " + maxProfitItem + " с прибылью " + maxProfit);
    }

    public void getMaxSpendOrder(int month) {
        int maxSpend = 0;
        String maxSpendItem = null;
        ArrayList<Transaction> transactions = monthlyTransactions.get(month);
        if (transactions != null) {
            for (Transaction transaction : transactions) {
                if (transaction.isExpense) {
                    int spend = transaction.quantity * transaction.unitPrice;
                    if (spend > maxSpend) {
                        maxSpend = spend;
                        maxSpendItem = transaction.itemName;
                    }
                }
            }
        }
        System.out.println("Самая большая трата за месяц " + month + ": " + maxSpendItem + " с затратами " + maxSpend);
    }
    public static void printMonthlyReports() {
        if (monthlyReports.isEmpty()) {
            System.out.println("Месячные отчеты не загружены.");
            return;
        }

        System.out.println("Информация о месячных отчетах:");
        for (Transaction transaction : monthlyReports) {
            System.out.print("Товар: " + transaction.itemName + ", Тип: ");
            if (transaction.isExpense) {
                System.out.print("Расход");
            } else {
                System.out.print("Доход");
            }
            System.out.println(", Количество: " + transaction.quantity +
                    ", Цена за единицу: " + transaction.unitPrice);
        }
    }

    public static void printYearlyReports() {
        if (yearlyReports.isEmpty()) {
            System.out.println("Годовые отчеты не загружены.");
            return;
        }

        System.out.println("Информация о годовых отчетах:");
        for (MonthTotalPerYear reportItem : yearlyReports) {
            String type;
            if (reportItem.isExpense) {
                type = "Расход";
            } else {
                type = "Доход";
            }
            System.out.println("Месяц: " + reportItem.month + ", Сумма: " + reportItem.amount + ", Тип: " + type);
        }
    }
}


