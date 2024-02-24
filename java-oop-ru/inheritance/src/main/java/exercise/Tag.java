package exercise;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class Tag {
    protected final String name;
    protected final Map<String, String> attributes;

    protected Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    protected String attributesToString() {
        return attributes.entrySet().stream()
                .map(attribute -> String.format("%s=\"%s\"", attribute.getKey(), attribute.getValue()))
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        return String.format("<%s%s%s>", name, attributes.isEmpty() ? "" : " ", attributesToString());
    }
}
