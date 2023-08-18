package world.property.generator.random.impl.numeric;

public class RandomFloatGenerator extends AbstractNumericRandomGenerator<Double> {

    public RandomFloatGenerator(Double from, Double to) {
        super(from, to);
    }

    @Override
    public Double generateValue() {
        Double from = range.getFrom();
        Double to = range.getTo();
        if(from == -1 || to == -1){
            return random.nextDouble();
        }
        return from + random.nextDouble() * (to - from);
    }
}