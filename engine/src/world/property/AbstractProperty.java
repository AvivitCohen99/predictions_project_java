package world.property;

import world.property.generator.api.ValueGenerator;
import world.property.generator.random.api.AbstractRandomValueGenerator;
import world.property.generator.random.impl.numeric.NumericRange;

import java.util.Optional;

public abstract class AbstractProperty<T> implements Property<T> {
    String name;
    PropertyType type;
    ValueGenerator<T> valueGenerator;
    T value;

    public AbstractProperty(String name, PropertyType type, ValueGenerator valueGenerator) {
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
        Optional<NumericRange> optionalRange = valueGenerator.getRange();
        if (optionalRange.isPresent()) {
            NumericRange range = optionalRange.get();
            return new PropertyDetails(name, type, Optional.ofNullable(range.getFrom()), Optional.ofNullable(range.getTo()), (this.valueGenerator instanceof AbstractRandomValueGenerator));
        }
        return new PropertyDetails(name, type, null, null, (this.valueGenerator instanceof AbstractRandomValueGenerator));
    }
}
