package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
import static exercise.Utils.serialize;
import static exercise.Utils.writeFile;
import static exercise.Utils.readFile;
import static exercise.Utils.unserialize;

public class FileKV implements KeyValueStorage {
    private final String pathToFile;

    public FileKV(String path, Map<String, String> storage) {
        this.pathToFile = path;
        writeFile(path, serialize(storage));
    }

    @Override
    public void set(String key, String value) {
        var storage = getFile();
        storage.put(key, value);
        saveFile(storage);
    }

    @Override
    public void unset(String key) {
        var storage = getFile();
        storage.remove(key);
        saveFile(storage);
    }

    @Override
    public String get(String key, String defaultValue) {
        var storage = getFile();
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(getFile());
    }

    private Map<String, String> getFile() {
        return unserialize(readFile(pathToFile));
    }

    private void saveFile(Map<String, String> storage) {
        writeFile(pathToFile, serialize(storage));
    }
}
// END
