import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        StepTracker stepTracker = new StepTracker();


        while (true) {
            switch (userInput) {
                case 1:
                    System.out.println("Введите месяц");
                    String month = scanner.next();
                    System.out.println("Введите день");
                    int day = scanner.nextInt();
                    while ((day <= 0) || (day > 30)) {
                        System.out.println("Номер дня введен некорректно. Введите заново.");
                        day = scanner.nextInt();
                    }
                    while (stepTracker.checkDay(month, day)) {
                        System.out.println("Данные за этот день уже введены или Вы пытаетесь ввести данные из прошлого. Введите заново.");
                        day = scanner.nextInt();
                    }
                    System.out.println("Введите количество шагов");
                    int stepsCount = scanner.nextInt();
                    while (stepsCount < 0) {
                        System.out.println("Количество шагов введено некорректно. Введите заново.");
                        stepsCount = scanner.nextInt();
                    }
                    stepTracker.daysToMonth(month, day, stepsCount);
                    break;
                case 2:
                    System.out.println("Введите месяц");
                    month = scanner.next();
                    stepTracker.monthSteps(month);
                    stepTracker.maxStepsInMonth(month);
                    stepTracker.distanceKmKalInMonth(month);
                    stepTracker.bestResult(month);
                    break;
                case 3:
                    System.out.println("Введите новую цель");
                    int newGoal = scanner.nextInt();
                    System.out.println("Теперь новая цель " + stepTracker.changeGoal(newGoal) + " шагов.");
                    break;
                case 4:
                    System.out.println("Программа завершена");
                    return;
                default:
                    System.out.println("Такой команды не существует. Введите команду из меню.");
                    break;
            }

            printMenu(); // печатем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
    }

    private static void printMenu() {
        System.out.println("Что Вы хотите сделать?");
        System.out.println("1 - Ввести количество шагов за определённый день.");
        System.out.println("2 - Напечатать статистику за определённый месяц.");
        System.out.println("3 - Изменить цель по количеству шагов в день.");
        System.out.println("4 - Выйти из приложения.");
    }
}