package world.property.generator.random.api;


import world.property.generator.api.ValueGenerator;

import java.util.Random;

public abstract class AbstractRandomValueGenerator<T> implements ValueGenerator<T> {
    protected final Random random;

    protected AbstractRandomValueGenerator() {
        random = new Random();
    }
}
