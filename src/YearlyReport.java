import java.util.ArrayList;

public class YearlyReport {
    private ArrayList<MonthTotalPerYear> reports;

    public YearlyReport() {
        reports = new ArrayList<>();
    }

    public void addReportItem (MonthTotalPerYear item) {
        reports.add(item);
    }

    public void printReport() {
        System.out.println("Годовой отчет:");
        for (MonthTotalPerYear item : reports) {
            String type;
            if (item.isExpense) {
                type = "Расход";
            } else {
                type = "Доход";
            }
            System.out.println("Месяц: " + item.month +
                    ", Сумма: " + item.amount +
                    ", Тип: " + type);
        }
    }
}
