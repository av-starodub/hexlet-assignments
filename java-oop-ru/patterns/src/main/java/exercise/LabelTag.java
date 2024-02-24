package exercise;

public class LabelTag implements TagInterface {
    private final String tagName;
    private final TagInterface child;

    public LabelTag(String tagName, TagInterface child) {
        this.tagName = tagName;
        this.child = child;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", tagName, child.render());
    }
}
