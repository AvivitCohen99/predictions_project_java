package world.property;

import world.property.generator.api.ValueGenerator;

public abstract class AbstractProperty<T> implements Property<T>{
    String name;
    PropertyType type;
    ValueGenerator valueGenerator;
    T value;

    public AbstractProperty(String name, PropertyType type, ValueGenerator valueGenerator){
        this.name = name;
        this.type = type;
        this.valueGenerator = valueGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PropertyType getType() {
        return type;
    }

    @Override
    public T generateValue() {
        return null;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}
