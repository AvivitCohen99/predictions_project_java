package world.property.generator.api;

import world.property.generator.random.impl.numeric.NumericRange;

import java.util.Optional;

public interface ValueGenerator<T> {
    T generateValue();
    Optional<NumericRange> getRange();
}