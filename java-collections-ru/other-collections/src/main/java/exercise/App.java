package exercise;

import java.util.*;

// BEGIN
public class App {
    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> diff = new LinkedHashMap<>();
        getUniqueKeys(data1, data2).forEach(key -> {
            var value1 = data1.get(key);
            var value2 = data2.get(key);
            if (Objects.nonNull(value1) && Objects.nonNull(value2)) {
                diff.put(key, value1.equals(value2) ? "unchanged" : "changed");
            } else {
                diff.put(key, Objects.isNull(value1) ? "added" : "deleted");
            }
        });
        return diff;
    }

    private static Set<String> getUniqueKeys(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        return keys;
    }
}
//END
