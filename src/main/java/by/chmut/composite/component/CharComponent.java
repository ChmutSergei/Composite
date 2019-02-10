package by.chmut.composite.component;

public class CharComponent extends Component{

    private char content;

    public CharComponent(ComponentType type, char content) {
        super(type);
        this.content = content;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CharComponent component = (CharComponent) object;
        return content == component.content;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result*31 + Character.hashCode(content);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(content);
    }
}
