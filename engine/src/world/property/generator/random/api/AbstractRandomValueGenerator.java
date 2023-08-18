package world.property.generator.random.api;


import world.property.generator.api.ValueGenerator;
import world.property.generator.random.impl.numeric.NumericRange;

import java.util.Optional;
import java.util.Random;

public abstract class AbstractRandomValueGenerator<T> implements ValueGenerator<T> {
    protected final Random random;

    protected AbstractRandomValueGenerator() {
        random = new Random();
    }

    @Override
    public Optional<NumericRange> getRange() {
        return Optional.empty();
    }
}

