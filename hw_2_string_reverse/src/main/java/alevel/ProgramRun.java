package alevel;

import alevel.stringreverse.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRun {

    public static void run() {
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {
                    case "1": {
                        reverseAllWords(reader);
                    }
                    break;
                    case "2": {
                        reversePartOfWord(reader);
                    }
                    break;
                    case "3": {
                        reversePartOfWordOfLine(reader);
                    }
                    break;
                    case "0": {
                        System.exit(0);
                    }
                    break;
                }
                preview();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void reversePartOfWordOfLine(BufferedReader reader) throws IOException {
        System.out.println("Enter your line:");
        String line = reader.readLine();
        System.out.println("Enter your starting index:");
        int startIndex = Integer.parseInt(reader.readLine());
        System.out.println("Enter your ending index:");
        int endingIndex = Integer.parseInt(reader.readLine());
        String reversedLine = StringUtils.reverse(line, startIndex, endingIndex);
        System.out.println("Reversed line:\n" + reversedLine);
    }

    private static void reversePartOfWord(BufferedReader reader) throws IOException {
        System.out.println("Enter your line:");
        String line = reader.readLine();
        System.out.println("Enter your part of word:");
        String partOfWord = reader.readLine();
        String reversedLine = StringUtils.reverse(line, partOfWord);
        System.out.println("Reversed line:\n" + reversedLine);
    }

    private static void reverseAllWords(BufferedReader reader) throws IOException {
        System.out.println("Enter your line:");
        String reversedLine = StringUtils.reverse(reader.readLine());
        System.out.println("Reversed line:\n" + reversedLine);
    }

    private static void preview() {
        System.out.println();
        System.out.println("if you want to reverse all the words, please select 1");
        System.out.println("if you need to reverse a part of line, please select 2");
        System.out.println("if you need to reverse a part of line set by the indexes, please select 3");
        System.out.println("if you need exit task, please select 0");
        System.out.println("Select you event:");
    }
}
