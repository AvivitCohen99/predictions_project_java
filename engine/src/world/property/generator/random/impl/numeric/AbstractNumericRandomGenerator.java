package world.property.generator.random.impl.numeric;

import world.property.generator.random.api.AbstractRandomValueGenerator;

public abstract class AbstractNumericRandomGenerator<T> extends AbstractRandomValueGenerator<T> {

    protected final T from;
    protected final T to;

    protected AbstractNumericRandomGenerator(T from, T to) {
        super();
        this.from = from;
        this.to = to;
    }

}