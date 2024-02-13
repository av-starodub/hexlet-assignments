package exercise;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class FileKVTest extends AbstractKeyValueStorageTest {

    private static final Path FILEPATH = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(FILEPATH, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @AfterAll
    static void clear() throws IOException {
        Files.delete(FILEPATH);
    }

    @Override
    protected KeyValueStorage createStorage(Map<String, String> storage) {
        return new FileKV("src/test/resources/file", storage);
    }
    // END
}
