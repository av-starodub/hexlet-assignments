package exercise;

import java.util.Map;

class InMemoryKVTest extends AbstractKeyValueStorageTest{

    @Override
    protected KeyValueStorage createStorage(Map<String, String> storage) {
        return new InMemoryKV(storage);
    }
}
