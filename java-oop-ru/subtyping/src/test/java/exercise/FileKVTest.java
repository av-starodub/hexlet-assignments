package exercise;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

class FileKVTest extends AbstractKeyValueStorageTest {

    private static final Path FILEPATH = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(FILEPATH, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Override
    protected KeyValueStorage createStorage(Map<String, String> storage) {
        return new FileKV("src/test/resources/file", storage);
    }
    // END
}
