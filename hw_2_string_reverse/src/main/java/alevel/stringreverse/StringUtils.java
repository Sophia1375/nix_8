package alevel.stringreverse;

public class StringUtils {

    public static String reverse(String src) {
        if (src == null) {
            return null;
        }
        String[] arrayOfWords = src.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrayOfWords.length; i++) {
            String word = arrayOfWords[i];
            for (int j = word.length() - 1; j >= 0; j--) {
                result.append(word.charAt(j));
            }
            result.append(" ");
        }
        if (result.length() > src.length()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    public static String reverse(String src, String dest) {
        if (src == null) {
            return null;
        }
        if (dest == null) {
            return src;
        }
        StringBuilder result = new StringBuilder();

        String[] splitSrc = src.split(dest);
        for (String part : splitSrc) {
            result.append(part);
            if (result.length() >= src.length()) {
                break;
            }
            boolean isLastCharSpace = dest.charAt(dest.length() - 1) == ' ';
            String[] destParts = dest.split(" ");
            for (String destPart : destParts) {
                for (int i = destPart.length() - 1; i >= 0; i--) {
                    result.append(destPart.charAt(i));
                }
                result.append(" ");
            }
            if (!isLastCharSpace) {
                result.deleteCharAt(result.length() - 1);
            }
        }
        return result.toString();
    }

    public static String reverse(String src, int firstIndex, int
            lastIndex) {
        if (src == null) {
            return null;
        }
        return reverse(src, src.substring(firstIndex, lastIndex + 1));
    }
}
