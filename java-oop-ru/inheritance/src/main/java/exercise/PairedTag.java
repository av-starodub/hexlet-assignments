package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public final class PairedTag extends Tag {
    private final String body;
    private final List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        super(tagName, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s</%s>", super.toString(), body, childrenToString(), name);
    }

    private String childrenToString() {
        return children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
    }
}
