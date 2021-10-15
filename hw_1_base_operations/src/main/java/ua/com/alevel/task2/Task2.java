package ua.com.alevel.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Task2 {

    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task2.run");
        System.out.println("Enter the string of characters: ");
        String line = reader.readLine();
        char[] charArray = line.toCharArray();
        Map<Character, Integer> mapCharacterToCounter = new HashMap<>();
        for (char character : charArray) {
            if (Character.isLetter(character)) {
                Integer counter = mapCharacterToCounter.get(character);
                if (counter == null) {
                    mapCharacterToCounter.put(character, 1);
                } else {
                    mapCharacterToCounter.put(character, counter + 1);
                }
            }
        }
        Set<Map.Entry<Character, Integer>> entrySet = mapCharacterToCounter.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(entrySet);
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        System.out.println("Sum of particular character:");
        for (Map.Entry<Character, Integer> keyValue : list) {
            System.out.println(keyValue.getKey() + ":" + keyValue.getValue());
        }

    }
}
