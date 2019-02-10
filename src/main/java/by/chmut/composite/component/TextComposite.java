package by.chmut.composite.component;

import java.util.*;

public class TextComposite extends Component {

    private static final String TABULATION = "\t";
    private static final String LINE_FEED = "\n";
    private static final String SPACE = "\u0020";

    private List<Component> components = new ArrayList<>();

    public TextComposite(ComponentType type) {
        super(type);
    }

    public List<Component> getComponents() {
        return Collections.unmodifiableList(components);
    }

    public void add(Component component) {
        components.add(component);
    }

    public Component getChild(int index) {
        return components.get(index);
    }

    public boolean remove(TextComposite component) {
        return components.remove(component);
    }

    public void sort(ComponentType componentType, Comparator<Component> comparator) {
        if (this.getType() == componentType) {
            components.sort(comparator);
        } else {
            for (Component component : components) {
                if (component instanceof TextComposite) {
                    ((TextComposite) component).sort(componentType, comparator);
                }
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TextComposite that = (TextComposite) object;
        return Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (Component component:components){
            result = result*component.hashCode();
        }
        return result;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.getType().equals(ComponentType.TEXT)) {
            components.stream()
                    .map(Component::toString)
                    .map(StringBuilder::new)
                    .forEach(o -> stringBuilder.append(TABULATION).append(o).append(LINE_FEED));
        } else if (this.getType().equals(ComponentType.SENTENCE)) {
            components.stream()
                    .map(Component::toString)
                    .map(StringBuilder::new)
                    .forEach(o -> stringBuilder.append(o).append(SPACE));
        } else {
            components.stream()
                    .map(Component::toString)
                    .map(StringBuilder::new)
                    .forEach(stringBuilder::append);
        }
        return stringBuilder.toString();
    }
}
