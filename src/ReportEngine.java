import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {
    static ArrayList<MonthTotalPerYear> yearlyReports = new ArrayList<>();
    static HashMap<String, ArrayList<Transaction>> monthlyTransactions = new HashMap<>();
    static ArrayList<Transaction> monthlyReports = new ArrayList<>();
    public static void readMonthlyReports() {
        FileReader fileReader = new FileReader();
        String[] fileNames = {"m.202101.csv", "m.202102.csv", "m.202103.csv"};
        monthlyReports.clear();
        for (String fileName : fileNames) {
            ArrayList<String> lines = fileReader.readFileContents(fileName);
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                Transaction transaction = new Transaction(parts[0], Boolean.parseBoolean(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                monthlyReports.add(transaction);
            }
        }
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

        for (String month : monthlyTransactions.keySet()) {
            int totalIncome = 0;
            int totalExpense = 0;

            for (Transaction transaction : monthlyTransactions.get(month)) {
                int amount = transaction.quantity * transaction.unitPrice;
                if (transaction.isExpense) {
                    totalExpense += amount;
                } else {
                    totalIncome += amount;
                }
            }
            YearlyData yearlyData = yearlyReports.get(month);
            if (yearlyData != null) {
                System.out.println("Отчет за месяц " + month + ":");
                System.out.println("Общий доход: " + totalIncome + " / Доход по годовому отчету: " + yearlyData.getIncome());
                System.out.println("Общий расход: " + totalExpense + " / Расход по годовому отчету: " + yearlyData.getExpense());

                // Проверка на соответствие
                if (totalIncome == yearlyData.getIncome() && totalExpense == yearlyData.getExpense()) {
                    System.out.println("Месячные данные соответствуют годовому отчету.");
                } else {
                    System.out.println("Расхождение в данных месячного и годового отчетов.");
                }
            } else {
                System.out.println("Годовой отчет за месяц " + month + " отсутствует.");
            }


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


