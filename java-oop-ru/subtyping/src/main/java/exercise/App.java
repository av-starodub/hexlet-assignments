package exercise;

// BEGIN
public final class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        for (var entry : storage.toMap().entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            storage.set(value, key);
            storage.unset(key);
        }
    }
}
// END
