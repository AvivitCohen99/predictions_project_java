package world.property.generator.fixed;

import world.property.generator.api.ValueGenerator;
import world.property.generator.random.impl.numeric.NumericRange;

import java.util.Optional;

public class FixedValueGenerator<T> implements ValueGenerator<T> {

    private final T fixedValue;

    public FixedValueGenerator(T fixedValue) {
        this.fixedValue = fixedValue;
    }

    @Override
    public T generateValue() {
        return fixedValue;
    }

    @Override
    public Optional<NumericRange> getRange() {
        return Optional.empty();
    }
}
