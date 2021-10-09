package holovko.sofia.hw.one;

import java.util.Scanner;


public class TaskOne {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String line = console.nextLine();
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) {
                int intNumber = Character.getNumericValue(ch);
                sum = sum + intNumber;
            }
        }
        System.out.println(sum);
    }
}
