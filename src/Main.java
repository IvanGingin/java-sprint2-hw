import java.util.Scanner;
enum Command {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE

}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 0) {
                System.out.println("Выход");
                break;
            }
            if (command < 1 || command > Command.values().length){

                System.out.println("Извините, такой команды пока нет.");
                continue;
            }
            Command commandName = Command.values()[command - 1];
            switch (commandName) {
                case ONE:
                    MonthlyReport.readMonthlyReports(); break;
                case TWO:
                    YearlyReport.readYearlyReports(); break;
                case THREE:
                    ReportEngine.compareReports(); break;
                case FOUR:
                    MonthlyReport.printMonthlyReports(); break;
                case FIVE:
                    YearlyReport.printYearlyReports(); break;
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

