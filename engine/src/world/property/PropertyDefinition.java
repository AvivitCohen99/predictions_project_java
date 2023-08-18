package world.property;

public interface PropertyDefinition<T> {
    String getName();
    PropertyType getType();
    T generateValue();
    void setValue(T value);
    T getValue();
    PropertyDetails getDetails();
}
