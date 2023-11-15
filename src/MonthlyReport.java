import java.util.ArrayList;
import java.util.HashMap;
public class MonthlyReport {

    static ArrayList<Transaction> monthlyReports = new ArrayList<>();

    public static void readMonthlyReports() {
        FileReader fileReader = new FileReader();
        String[] fileNames = {"m.202101.csv", "m.202102.csv", "m.202103.csv"};
        monthlyReports.clear();
        for (String fileName : fileNames) {
            ArrayList<String> lines = fileReader.readFileContents(fileName);
            String[] fileNameParts = fileName.split("\\."); // Разделение имени файла на части
            String month = fileNameParts[1];
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                Transaction transaction = new Transaction(parts[0], Boolean.parseBoolean(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), month);
                monthlyReports.add(transaction);
            }
        }
    }
    public static void printMonthlyReports() {
        if (monthlyReports.isEmpty()) {
            System.out.println("Месячные отчеты не загружены.");
            return;
        }

        System.out.println("Информация о месячных отчетах:");
        for (Transaction transaction : monthlyReports) {
            System.out.print("Товар: " + transaction.name + ", Тип: ");
            if (transaction.isExpense) {
                System.out.print("Расход");
            } else {
                System.out.print("Доход");
            }
            System.out.println(", Количество: " + transaction.quantity +
                    ", Цена за единицу: " + transaction.unitPrice);
        }
    }
}