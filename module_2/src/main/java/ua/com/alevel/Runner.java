package ua.com.alevel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
    void run() {
        //Task One
        convertDates();
        //Task Two
        findUniqueNames();
    }

    private void findUniqueNames() {
        try (InputStream in = getClass().getResourceAsStream("/input2.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            final List<String> inputNames = reader.lines().collect(Collectors.toList());
            final UniqueNameFinder uniqueNameFinder = new UniqueNameFinder();
            final String uniqueName = uniqueNameFinder.findUniqueName(inputNames);
            FileOutputStream fos = new FileOutputStream("module_2/output2.txt");
            fos.write(String.join("\n", uniqueName).getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertDates() {
        try (InputStream in = getClass().getResourceAsStream("/input1.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            final List<String> inputDates = reader.lines().collect(Collectors.toList());
            final DateConverter dateConverter = new DateConverter();
            final ArrayList<String> outputDates = new ArrayList<>();
            for (String inputDate : inputDates) {
                outputDates.add(dateConverter.convert(inputDate));
            }
            FileOutputStream fos = new FileOutputStream("module_2/output1.txt");
            fos.write(String.join("\n", outputDates).getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Runner().run();
    }
}

