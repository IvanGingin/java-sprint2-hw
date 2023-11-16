import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    public static ArrayList<MonthTotalPerYear> yearlyReports = new ArrayList<>();

    public static void readYearlyReports() {
        FileReader fileReader = new FileReader();
        ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            MonthTotalPerYear item = new MonthTotalPerYear(parts[0], Integer.parseInt(parts[1]), Boolean.parseBoolean(parts[2]));
            yearlyReports.add(item);
        }
        System.out.println("Годовой отчет загружен!");
    }

    public static void printYearlyReports() {
        if (yearlyReports.isEmpty()) {
            System.out.println("Годовые отчеты не загружены.");
            return;
        }

        int totalExpense = 0;
        int totalIncome = 0;
        HashMap<String, Integer> monthlyProfit = new HashMap<>();

        for (MonthTotalPerYear reportItem : yearlyReports) {
            monthlyProfit.putIfAbsent(reportItem.month, 0);
            int currentProfit = monthlyProfit.get(reportItem.month);

            if (reportItem.isExpense) {
                totalExpense += reportItem.amount;
                currentProfit -= reportItem.amount;
            } else {
                totalIncome += reportItem.amount;
                currentProfit += reportItem.amount;
            }

            monthlyProfit.put(reportItem.month, currentProfit);
        }

        System.out.println("Информация о годовом отчете за 2021 год:");
        for (String month : monthlyProfit.keySet()) {
            System.out.println("Месяц: " + month + ", Прибыль: " + monthlyProfit.get(month));
        }

        int averageExpense = totalExpense / monthlyProfit.size();
        int averageIncome = totalIncome / monthlyProfit.size();

        System.out.println("Средний расход за год: " + averageExpense);
        System.out.println("Средний доход за год: " + averageIncome);
    }
}