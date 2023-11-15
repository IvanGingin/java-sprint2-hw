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