package ua.com.alevel.task1;

import java.io.BufferedReader;
import java.io.IOException;

public class Task1 {
    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task1.run");
        System.out.println("Enter the string of characters: ");
        String line = reader.readLine();
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                if (sb.length() > 0) {
                    sum += Integer.parseInt(sb.toString());//increase sum based on current number
                    sb.delete(0, sb.length()); // reset buffer for new number
                }
            }
        }
        if (sb.length() > 0)
            sum += Integer.parseInt(sb.toString());
        System.out.println(sum);
    }
}
