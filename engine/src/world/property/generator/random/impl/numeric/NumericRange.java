package world.property.generator.random.impl.numeric;

public class NumericRange<T extends Number> {
    final T from;
    final T to;

    public NumericRange(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }
}
