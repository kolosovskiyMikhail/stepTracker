import java.util.HashMap;
import java.util.ArrayList;

public class StepTracker {
    int goal = 10000;
    ArrayList<Integer> stepsInMonth;
    HashMap<String, ArrayList<Integer>> monthDay;
    Converter converter;
    ArrayList<Integer> checkDay;


    StepTracker() {
        stepsInMonth = new ArrayList<>();
        monthDay = new HashMap<>();
        converter = new Converter();
        checkDay = new ArrayList<>();
    }


    int changeGoal(int newGoal) { //Изменяем цель
        if (newGoal > 0) {
            goal = newGoal;
        } else {
            System.out.println("Некорректная цель");
        }
        return goal;
    }

    void saveSteps(int day, int stepsCount) { //Добавляем шаги
        for (int i = 0; i < 30; i++) {
            stepsInMonth.add(0);
        }
        stepsInMonth.add(day - 1, stepsCount);
        while (stepsInMonth.size() > 30) {
            stepsInMonth.remove((stepsInMonth.size() - 1));
        }
    }

    void daysToMonth(String month, int day, int stepsCount) { // Сохраняем количество шагов в дни месяца
        if (monthDay.containsKey(month)) {
            stepsInMonth = monthDay.get(month);
            saveSteps(day, stepsCount);
        } else {
            stepsInMonth = new ArrayList<>();
            saveSteps(day, stepsCount);
            monthDay.put(month, stepsInMonth);
        }
    }

    void monthSteps(String month) { //Выводим количество шагов по дням
        int d = 0;
        System.out.println("Статистика за " + month + ":");
        if (!monthDay.containsKey(month)) {
            System.out.println("Такого месяца нет!");
            return;
        }
        stepsInMonth = monthDay.get(month);
        for (Integer st : stepsInMonth) {
            d++;
            if (d < 30) {
                System.out.print(d + " день: " + st + ", ");
            } else {
                System.out.print(d + " день: " + st + ".");
            }
        }
        System.out.println();
    }

    void maxStepsInMonth(String month) {  //Ищем максимальное количество шагов
        int maxSteps = 0;
        if (!monthDay.containsKey(month)) {
            System.out.println("Такого месяца нет!");
            return;
        }
        stepsInMonth = monthDay.get(month);
        for (Integer st : stepsInMonth) {
            if (st > maxSteps) {
                maxSteps = st;
            }
        }
        System.out.println("Максимальное количество шагов - " + maxSteps);
    }

    void distanceKmKalInMonth(String month) { //Ищем среднее количество шагов, километры и каллории
        int sumSteps = 0;
        if (!monthDay.containsKey(month)) {
            System.out.println("Такого месяца нет!");
            return;
        }
        stepsInMonth = monthDay.get(month);
        for (Integer st : stepsInMonth) {
            sumSteps += st;
        }
        System.out.println("Среднее количество шагов - " + sumSteps / 30);
        System.out.println("Пройденная дистанция - " + converter.stepToKm(sumSteps) + " км");
        System.out.println("Потрачено кКал - " + converter.calcKalor(sumSteps));
    }

    void bestResult(String month) { //Ищем лучшую серию шагов в месяце
        int bestCount = 0;
        int maxDays = 0;
        if (!monthDay.containsKey(month)) {
            System.out.println("Такого месяца нет!");
            return;
        }
        stepsInMonth = monthDay.get(month);
        for (int i = 0; i < stepsInMonth.size(); i++) {
            if (stepsInMonth.get(i) > goal) {
                bestCount++;
                if (stepsInMonth.get(i) == stepsInMonth.get(stepsInMonth.size() - 1)) {
                    if (bestCount > maxDays) {
                        maxDays = bestCount;
                    }
                }
            } else {
                if (bestCount > maxDays) {
                    maxDays = bestCount;
                }
                bestCount = 0;
            }
        }
        System.out.println("Лучшая серия дней " + maxDays);
    }

    boolean checkDay(String month, int day) { //Проверка правильности ввода дня
        boolean check = false;
        if (monthDay.containsKey(month)) {
            stepsInMonth = monthDay.get(month);
            for (Integer st : stepsInMonth) {
                if (st > 0) {
                    checkDay.add(stepsInMonth.indexOf(st));
                }
            }
        }
        for (Integer ch : checkDay) {
            if (ch >= day - 1) {
                check = true;
            }
        }
        return check;
    }
}