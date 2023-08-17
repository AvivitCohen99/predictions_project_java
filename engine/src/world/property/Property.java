package world.property;

public interface Property<T> {
    String getName();
    PropertyType getType();
    T generateValue();
    void setValue(T value);
    T getValue();
}
