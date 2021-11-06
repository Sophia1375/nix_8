package ua.com.alevel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueNumbers {
    private final String numbers;

    public UniqueNumbers(String numbers) {
        this.numbers = numbers;
    }

    public int getUniqueNumbers() {
        String[] split = numbers.split(" ");
        Set<String> uniqueNumbers = new HashSet<>(Arrays.asList(split));
        return uniqueNumbers.size();
    }
}
