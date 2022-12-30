package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


// BEGIN
public class Sorter {
    public static List<String> takeOldestMen(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> "male".equals(user.get("gender")))
                .sorted(Comparator.comparing(user -> parseDate(user.get("birthday"))))
                .map(user -> user.get("name"))
                .toList();
    }

    private static Date parseDate(String birthday) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
