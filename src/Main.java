import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                ReportEngine.readMonthlyReports();

            } else if (command == 2) {
                ReportEngine.readYearlyReports();

            } else if (command == 3) {
                ReportEngine.compareReports();

            } else if (command == 4) {
                ReportEngine.printMonthlyReports();

            } else if (command == 5) {
                ReportEngine.printYearlyReports();

            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }
    private static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}

