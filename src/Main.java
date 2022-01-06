import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        StepTracker stepTracker = new StepTracker();


        while (userInput != 0) {
           if (userInput == 1) {
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

           } else if  (userInput == 2) {
               System.out.println("Введите месяц");
               String month = scanner.next();
               stepTracker.monthSteps(month);
               stepTracker.maxStepsInMonth(month);
               stepTracker.distanceKmKalInMonth(month);
               stepTracker.bestResult(month);
           } else if  (userInput == 3) {
               System.out.println("Введите новую цель");
               int newGoal = scanner.nextInt();
               System.out.println("Теперь новая цель " + stepTracker.changeGoal(newGoal) + " шагов.");
           } else if  (userInput == 4) {
               break;
           }

            printMenu(); // печатем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя

        }
        System.out.println("Программа завершена");

    }

    private static void printMenu() {
        System.out.println("Что Вы хотите сделать?");
        System.out.println("1 - Ввести количество шагов за определённый день.");
        System.out.println("2 - Напечатать статистику за определённый месяц.");
        System.out.println("3 - Изменить цель по количеству шагов в день.");
        System.out.println("4 - Выйти из приложения.");
    }
}