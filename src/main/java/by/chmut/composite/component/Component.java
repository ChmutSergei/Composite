package by.chmut.composite.component;

public abstract class Component {

    private ComponentType type;

    public Component(ComponentType type) {
        this.type = type;
    }

    public ComponentType getType() {
        return type;
    }
}
