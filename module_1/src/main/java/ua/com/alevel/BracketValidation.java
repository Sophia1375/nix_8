package ua.com.alevel;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class BracketValidation {
    private String line;

    BracketValidation(String line) {
        this.line = line;
    }

    public boolean isValid() {
        HashSet<Character> acceptableBrackets = new HashSet<>();
        acceptableBrackets.add('(');
        acceptableBrackets.add(')');
        acceptableBrackets.add('{');
        acceptableBrackets.add('}');
        acceptableBrackets.add('[');
        acceptableBrackets.add(']');
        Deque<Character> queue = new LinkedList<>();
        for (char ch : line.toCharArray()) {
            if (!acceptableBrackets.contains(ch)) continue;
            if (queue.size() == 0) {
                if (ch == ')' || ch == '}' || ch == ']')
                    return false;
                queue.add(ch);
                continue;
            }
            Character lastChar = queue.pollLast();
            if (lastChar.equals('(') && ch == ')')
                continue;
            if (lastChar.equals('{') && ch == '}')
                continue;
            if (lastChar.equals('[') && ch == ']')
                continue;
            queue.add(lastChar);
            queue.add(ch);
        }

        return queue.isEmpty();
    }
}
