package lesson3;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class ArrayOfWords {
    private String words[] = {"Груша", "Яблоко", "Слива", "Вишня", "Черешня", "Абрикос"};

    public void arrayOfWords() {

        Random random = new Random();
        Map<String, Integer> countMap = new TreeMap<>();
        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(6);
            String fruit = words[number];
            Integer currentNumber = countMap.get(fruit);
            countMap.put(fruit, currentNumber == null ? 1 : currentNumber + 1);
        }
        System.out.println(countMap);
    }
}
