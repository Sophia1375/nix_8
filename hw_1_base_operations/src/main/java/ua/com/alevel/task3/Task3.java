package ua.com.alevel.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalTime;

public class Task3 {
    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task3.run");
        System.out.println("Enter lesson's number: ");
        countLessonsEndDuration(reader);
    }


    public static void countLessonsEndDuration(BufferedReader reader) throws IOException {
        int lessonCount = Integer.parseInt(reader.readLine());
        if (lessonCount < 1 || lessonCount > 10) {
            System.out.println("Invalid number. Please, enter the number between 1-10");
            return;
        }
        int lessonsDurationInMinutes = calculateDuration(lessonCount);
        LocalTime finishTime = LocalTime.parse("09:00").plusMinutes(lessonsDurationInMinutes);
        System.out.println(finishTime.getHour() + " " + finishTime.getMinute());
    }

    public static int calculateDuration(int lessonCount) {
        int lessonDuration = 45;
        int breakLength = lessonCount * 10;
        breakLength += lessonCount % 2 * -10 + (lessonCount + 1) % 2 * -15;
        return breakLength + (lessonDuration * lessonCount);
    }
}

