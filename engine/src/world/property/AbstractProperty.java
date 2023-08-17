package world.property;

import world.property.generator.api.ValueGenerator;

public abstract class AbstractProperty<T> implements Property<T>{
    String name;
    PropertyType type;
    ValueGenerator<T> valueGenerator;
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
        return valueGenerator.generateValue();
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public PropertyDetails getDetails() {
        return new PropertyDetails(name, type, null, null, true); // TODO: fix is random
    }
}
