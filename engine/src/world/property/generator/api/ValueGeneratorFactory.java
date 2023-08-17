package world.property.generator.api;

import world.property.generator.fixed.FixedValueGenerator;
import world.property.generator.random.impl.bool.RandomBooleanValueGenerator;
import world.property.generator.random.impl.numeric.RandomFloatGenerator;
import world.property.generator.random.impl.numeric.RandomIntegerGenerator;

public interface ValueGeneratorFactory {

    static <T> ValueGenerator<T> createFixed(T value) {
        return new FixedValueGenerator<>(value);
    }

    static ValueGenerator<Boolean> createRandomBoolean() {
        return new RandomBooleanValueGenerator();
    }

    static ValueGenerator<Integer> createRandomInteger(Integer from, Integer to) {
        return new RandomIntegerGenerator(from, to);
    }

    static ValueGenerator<Double> createRandomFloat(Double from, Double to) {
        return new RandomFloatGenerator(from, to);
    }

}