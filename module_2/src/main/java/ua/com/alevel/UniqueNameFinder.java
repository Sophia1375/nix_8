package ua.com.alevel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UniqueNameFinder {

    public String findUniqueName(List<String> names) {
    final HashMap<String, Integer> nameToCounter = new LinkedHashMap<>();
    names.forEach(name -> {
        nameToCounter .merge(name, 1, Integer::sum);
        });
        return nameToCounter.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("");

    }
}
