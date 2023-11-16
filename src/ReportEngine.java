import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {
    static ArrayList<MonthTotalPerYear> yearlyReports = new ArrayList<>();
    static ArrayList<Transaction> monthlyReports = new ArrayList<>();

    public static void compareReports() {
        if (MonthlyReport.monthlyTransactions.isEmpty() || YearlyReport.yearlyReports.isEmpty()) {
            System.out.println("Необходимо сначала считать все отчеты.");
            return;

        }
        boolean discrepancyFound = false;
        HashMap<String, Integer> monthlyIncome = new HashMap<>();
        HashMap<String, Integer> monthlyExpenses = new HashMap<>();

        for (Transaction transaction : monthlyReports) {
            String month = transaction.month;
            int amount = transaction.quantity * transaction.unitPrice;
            if (transaction.isExpense) {
                monthlyExpenses.put(month, monthlyExpenses.getOrDefault(month, 0) + amount);
            } else {
                monthlyIncome.put(month, monthlyIncome.getOrDefault(month, 0) + amount);
            }
        }
        for (MonthTotalPerYear yearReportItem : yearlyReports) {
            String month = yearReportItem.month;
            int reportedAmount = yearReportItem.amount;
            int calculatedAmount;
            if (yearReportItem.isExpense) {
                calculatedAmount = -monthlyExpenses.getOrDefault(month, 0);
            } else {
                calculatedAmount = monthlyIncome.getOrDefault(month, 0);
            }
            if (reportedAmount != calculatedAmount) {
                System.out.println("Несоответствие в месяце: " + month + ". Отчет: " + reportedAmount + ", Расчет: " + calculatedAmount);
                discrepancyFound = true;
            }
        }
        if (!discrepancyFound) {
            System.out.println("Все отчеты успешно сверены. Несоответствий не обнаружено.");
        }
    }
}
