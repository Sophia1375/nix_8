package ua.com.alevel.util;


import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProgramRun {
    private static MathSet mathSet = new MathSetImpl<>();

    public static void run() {
        System.out.println("A new MathSet is created.");
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {
                    case "1": {
                        System.out.println("Enter a new Integer: ");
                        String line = reader.readLine();
                        Integer integer = Integer.valueOf(line);
                        mathSet.add(integer);
                        System.out.println("New Integer was added to MathSet. Elements of the MathSet are: ");
                        System.out.println("Result: " + mathSet.toString());
                        System.out.println("Select your event:");

                    }
                    break;
                    case "2": {
                        System.out.println("Enter your Integers using space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");
                        final ArrayList<Integer> integers = new ArrayList<>();
                        for (int i = 0; i < array.length; i++) {
                            Integer integer = Integer.valueOf(array[i]);
                            integers.add(integer);
                        }
                        mathSet.add(integers.toArray(new Number[0]));
                        System.out.println("New Integers were added to MathSet.");
                        System.out.println("Result: " + mathSet.toString());
                        System.out.println("Select your event:");
                    }
                    break;
                    case "3": {
                        System.out.println("Enter Integers of the extra MathSet you want to join. Use space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");
                        final MathSet<Integer> integers = new MathSetImpl<>();
                        for (int i = 0; i < array.length; i++) {
                            Integer integer = Integer.valueOf(array[i]);
                            integers.add(integer);
                        }
                        mathSet.join(integers);
                        System.out.println("MathSets were joined.");
                        System.out.println("Result: " + mathSet.toString());
                        System.out.println("Select your event:");
                    }
                    break;
                    case "4": {
                        System.out.println("Enter Integers of the second MathSet using space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");
                        final MathSet<Integer> integers = new MathSetImpl<>();
                        for (int i = 0; i < array.length; i++) {
                            Integer integer = Integer.valueOf(array[i]);
                            integers.add(integer);
                        }
                        mathSet.intersection(integers);
                        System.out.println("Common elements of two MathSets are: ");
                        for (Number number : mathSet.toArray()) {
                            System.out.println(number + " ");
                            System.out.println("Select your event:");
                        }

                    }
                    break;
                    case "5": {
                        mathSet.sortDesc();
                        System.out.println("MathSet was sorted in a descending order.");
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");
                    }
                    break;
                    case "6": {
                        System.out.println("Enter the first and the last indexes of the MathSet part you want to sort in the descending order. Use space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");

                        Integer start = Integer.valueOf(array[0]);
                        Integer end = Integer.valueOf(array[1]);

                        mathSet.sortDesc(start, end);
                        System.out.println("Given part of the MathSet was sorted in a descending order.");
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");
                    }
                    break;
                    case "7": {
                        System.out.println("Enter the value of element from which you want to sort the MathSet in the descending order. Use space as delimiter: ");
                        String line = reader.readLine();
                        Integer integer = Integer.valueOf(line);
                        mathSet.sortDesc(integer);
                        System.out.println("Given part of the MathSet was sorted in a descending order.");
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");
                    }
                    break;
                    case "8": {
                        mathSet.sortAsc();
                        System.out.println("MathSet was sorted in a ascending order.");
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");

                    }
                    break;
                    case "9": {
                        System.out.println("Enter the first and the last indexes of the MathSet part you want to sort in the ascending order. Use space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");

                        Integer start1 = Integer.valueOf(array[0]);
                        Integer end1 = Integer.valueOf(array[1]);
                        mathSet.sortAsc(start1, end1);
                        System.out.println("Given part of the MathSet was sorted in a ascending order.");
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");
                    }
                    break;
                    case "10": {
                        System.out.println("Enter the value of element from which you want to sort the MathSet in the ascending order. Use space as delimiter: ");
                        String line = reader.readLine();
                        Integer integer = Integer.valueOf(line);
                        mathSet.sortAsc(integer);
                        System.out.println("Given part of the MathSet was sorted in a ascending order.");
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");
                    }
                    break;
                    case "11": {
                        System.out.println("Enter the index of element which you want to get: ");
                        String line = reader.readLine();
                        Integer integer = Integer.valueOf(line);
                        final Number number = mathSet.get(integer);
                        System.out.println("The given value is: " + number);
                        System.out.println("Select your event:");
                    }
                    break;
                    case "12": {
                        final Number max = mathSet.getMax();
                        System.out.println("The maximum value is: " + max);
                        System.out.println("Select your event:");

                    }
                    break;
                    case "13": {
                        final Number min = mathSet.getMin();
                        System.out.println("The minimum value is: " + min);
                        System.out.println("Select your event:");
                    }
                    break;
                    case "14": {
                        final Number average = mathSet.getAverage();
                        System.out.println("The average value is: " + average);
                        System.out.println("Select your event:");

                    }
                    break;
                    case "15": {
                        final Number median = mathSet.getMedian();
                        System.out.println("The median value is: " + median);
                        System.out.println("Select your event:");

                    }
                    break;
                    case "16": {
                        final Number[] numbers = mathSet.toArray();
                        System.out.println("MathSet was converted to the array");
                        System.out.print("Result: ");
                        for (Number number : numbers) {
                            System.out.print(number.toString() + " ");


                        }

                        System.out.println();
                        System.out.println("Select your event:");

                    }
                    break;
                    case "17": {
                        System.out.println("Enter the first and the last indexes of the MathSet part you want to convert into Array. Use space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");

                        Integer start = Integer.valueOf(array[0]);
                        Integer end = Integer.valueOf(array[1]);
                        System.out.println("The part of the MathSet was converted to the array");
                        final Number[] numbers = mathSet.toArray(start, end);
                        for (Number number : numbers) {
                            System.out.print(number.toString() + " ");
                        }
                        System.out.println();
                        System.out.println("Select your event:");
                    }
                    break;
                    case "18": {
                        System.out.println("Enter the first and the last indexes of the MathSet part you want to cut out. Use space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");

                        Integer start = Integer.valueOf(array[0]);
                        Integer end = Integer.valueOf(array[1]);
                        mathSet = mathSet.cut(start, end);
                        System.out.println("A new array was created");
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");

                    }
                    break;
                    case "19": {
                        mathSet.clear();
                        System.out.println("MathSet was deleted.");
                        System.out.println("Select your event:");

                    }
                    break;
                    case "20": {
                        System.out.println("Enter Integers you want to delete using space as delimiter: ");
                        String line = reader.readLine();
                        final String[] array = line.split(" ");
                        final ArrayList<Integer> integers = new ArrayList<>();
                        for (int i = 0; i < array.length; i++) {
                            Integer integer = Integer.valueOf(array[i]);
                            integers.add(integer);
                        }
                        mathSet.clear(integers.toArray(Integer[]::new));
                        System.out.println("Result: " + mathSet);
                        System.out.println("Select your event:");

                    }
                    break;
                    case "21": {
                        final int size = mathSet.size();
                        System.out.println("The size of MathSet is " + size);
                        System.out.println("Select your event:");

                    }
                    break;
                    case "0": {
                        System.exit(0);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void preview() {
        System.out.println();
        System.out.println("1 --> To add a new Integer");
        System.out.println("2 --> To add a few Integers");
        System.out.println("3 --> To join an additional MathSet");
        System.out.println("4 --> To get inner join");
        System.out.println("5 --> To sort elements of the MathSet in the descending order");
        System.out.println("6 --> To sort a part of the MathSet in the descending order");
        System.out.println("7 --> To sort a part of the MathSet from the particular element in the descending order");
        System.out.println("8 --> To sort elements of the MathSet in the ascending order");
        System.out.println("9 --> To sort a part of the MathSet in the ascending order");
        System.out.println("10 --> To sort a part of the MathSet from the particular element in the ascending order");
        System.out.println("11 --> To get at Integer by its index");
        System.out.println("12 --> To get a maximum value from the MathSet");
        System.out.println("13 --> To get a minimum value from the MathSet");
        System.out.println("14 --> To get an average from the MathSet");
        System.out.println("15 --> To get a median from the MathSet");
        System.out.println("16 --> To convert the MathSet to Array");
        System.out.println("17 --> To convert a part of the MathSet to Array");
        System.out.println("18 --> To cut out a part of the MathSet");
        System.out.println("19 --> To delete all of the elements from the MathSet");
        System.out.println("20 --> To delete a few elements from the MathSet");
        System.out.println("21 --> To get a size of the MathSet");
        System.out.println("0 --> To exit task");
        System.out.println("Select your event:");
    }
}
