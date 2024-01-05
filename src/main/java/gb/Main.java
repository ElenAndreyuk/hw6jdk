package gb;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.*;
import java.util.stream.Collectors;

/*
В качестве задачи предлагаю вам реализовать код для
демонстрации парадокса Монти Холла (Парадокс Монти Холла
— Википедия ) и наглядно убедиться в верности парадокса
(запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
● Создать свой Java Maven или Gradle проект;
● Самостоятельно реализовать прикладную задачу;
● Сохранить результат в HashMap<шаг теста, результат>
● Вывести на экран статистику по победам и поражениям
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer> result = new HashMap<>();
        HashMap<Integer, Integer> resultAfterShift = new HashMap<>();
       // double win = 0, winAfterShift = 0, lose = 0, loseAfterShift = 0;

        for (int i = 0; i < 1000; i++) {
            List<String> doors = createMontyHallDoors();
            int choice = new Random().nextInt(3);
            if (doors.get(choice).equals("car")) {
                result.put(i, 1);
                doors.remove(choice);
            } else {
                result.put(i, 0);
            }
            doors.remove("goat");
            if (doors.contains("car")) {
                resultAfterShift.put(i, 1);
            } else {
                resultAfterShift.put(i, 0);
            }
        }
        System.out.println("Количество побед, без смены выбора - "+ statistics(result).getSum() );
        System.out.println("Количество побед, при смене выбора - " + statistics(resultAfterShift).getSum());
    }

    private static List<String> createMontyHallDoors() {
        List<String> threeDoors = new ArrayList<>();
        Collections.addAll(threeDoors, "goat", "goat", "goat");
        int carIndex = new Random().nextInt(3);
        threeDoors.set(carIndex, "car");
        return threeDoors;
    }

    public static  DescriptiveStatistics statistics(HashMap<Integer, Integer> result){
        DescriptiveStatistics ds = new DescriptiveStatistics();
        for (Integer integer : (result.values().stream().sorted().collect(Collectors.toList()))) {
            ds.addValue(integer);
        }
        return ds;
    }
}