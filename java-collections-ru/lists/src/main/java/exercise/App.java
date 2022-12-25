package exercise;

import java.util.ArrayList;
import java.util.stream.IntStream;

// BEGIN
public class App {
    public static boolean scrabble(String characterSet, String word) {
        if (characterSet.length() < word.length()) {
            return false;
        }
        var chars = new ArrayList<>(IntStream.range(0, characterSet.length())
                .mapToObj(characterSet::charAt).toList());
        for (char s : word.toLowerCase().toCharArray()) {
            if (!chars.remove(Character.valueOf(s))) {
                return false;
            }
        }
        return true;
    }
}
//END
