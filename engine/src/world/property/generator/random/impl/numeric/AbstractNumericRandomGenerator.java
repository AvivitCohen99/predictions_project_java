package world.property.generator.random.impl.numeric;

import world.property.generator.random.api.AbstractRandomValueGenerator;

import java.util.Optional;

public abstract class AbstractNumericRandomGenerator<T extends Number> extends AbstractRandomValueGenerator<T> {

    protected final NumericRange<T> range;

    protected AbstractNumericRandomGenerator(T from, T to) {
        super();
        this.range = new NumericRange<>(from, to);
    }

    @Override
    public Optional<NumericRange> getRange() {
        return Optional.ofNullable(range);
    }
}