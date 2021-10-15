package ua.com.alevel.task1;

import java.io.BufferedReader;
import java.io.IOException;

public class Task1 {
    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task1.run");
        System.out.println("Enter the string of characters: ");
        String line = reader.readLine();
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) {
                int intNumber = Character.getNumericValue(ch);
                sum = sum + intNumber;
            }
        }
        System.out.println("sum of digits = " + sum);
    }
}
