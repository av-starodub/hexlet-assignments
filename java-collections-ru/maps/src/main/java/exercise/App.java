package exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        if (!sentence.isEmpty()) {
            Arrays.asList(sentence.split(" ")).forEach(word -> wordCount.merge(word, 1, Integer::sum));
        }
        return wordCount;
    }

    public static String toString(Map<String, Integer> wordCount) {
        if (wordCount.isEmpty()) {
            return "{}";
        }
        var wordCountRepresentation = new StringBuilder();
        wordCountRepresentation.append("{\n");
        wordCount.forEach((k, v) -> wordCountRepresentation.append(String.format("  %s: %d\n", k, v)));
        wordCountRepresentation.append("}");
        return wordCountRepresentation.toString();
    }
}
//END
