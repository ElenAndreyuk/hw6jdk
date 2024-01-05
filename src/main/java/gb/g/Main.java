package gb.g;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
В качестве задачи предлагаю вам реализовать код для
демонстрации парадокса Монти Холла (Парадокс Монти Холла
— Википедия) и наглядно убедиться в верности парадокса
(запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
● Создать свой Java Maven или Gradle проект;
● Самостоятельно реализовать прикладную задачу;
● Сохранить результат в HashMap<шаг теста, результат>
● Вывести на экран статистику по победам и поражениям
*/

public class Main {
    public static void main(String[] arg) {
        double win = 0, winAfterShift = 0, lose = 0, loseAfterShift = 0;

        for (int i = 0; i < 1000; i++) {
            List<String> doors = createMontyHallDoors();
            int choice = new Random().nextInt(3);
            if (doors.get(choice).equals("car")) {
                win++;
                doors.remove(choice);
            } else lose++;
            doors.remove("goat");
            if (doors.contains("car")) {
                winAfterShift++;
            } else loseAfterShift++;
        }

        double result = win / (win + lose) * 100;
        double resultAfterShift = winAfterShift / (winAfterShift + loseAfterShift) * 100;

        System.out.printf("Процент побед, без смены выбора - %f%%\n", result);
        System.out.printf("Процент побед, при смене выбора - %s%%", resultAfterShift);
    }

    private static List<String> createMontyHallDoors() {
        List<String> threeDoors = new ArrayList<>();
        Collections.addAll(threeDoors, "goat", "goat", "goat");
        int carIndex = new Random().nextInt(3);
        threeDoors.set(carIndex, "car");
        return threeDoors;
    }
}