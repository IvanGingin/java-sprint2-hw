import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    static ArrayList<MonthTotalPerYear> yearlyReports = new ArrayList<>();
    public static void readYearlyReports() {
        FileReader fileReader = new FileReader();
        ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            MonthTotalPerYear item = new MonthTotalPerYear(parts[0], Integer.parseInt(parts[1]), Boolean.parseBoolean(parts[2]));
            yearlyReports.add(item);
        }
    }
    public static void printYearlyReports() {
        if (yearlyReports.isEmpty()) {
            System.out.println("Годовые отчеты не загружены. Сначала считайте данные.");
            return;
        }

        int totalExpense = 0;
        int totalIncome = 0;

        System.out.println("Информация о годовом отчете за 2021 год:");
        for (MonthTotalPerYear reportItem : yearlyReports) {
            int profit = 0;
            if (reportItem.isExpense) {
                totalExpense += reportItem.amount;
            } else {
                profit = reportItem.amount;
                totalIncome += profit;
            }
            System.out.println("Месяц: " + reportItem.month + ", Прибыль: " + profit);
        }

        int averageExpense = totalExpense / yearlyReports.size();
        int averageIncome = totalIncome / yearlyReports.size();

        System.out.println("Средний расход за год: " + averageExpense);
        System.out.println("Средний доход за год: " + averageIncome);
    }
}