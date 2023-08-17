package world.property.generator.random.impl.numeric;

public class RandomIntegerGenerator extends AbstractNumericRandomGenerator<Integer> {

    public RandomIntegerGenerator(Integer from, Integer to) {
        super(from, to);
    }

    @Override
    public Integer generateValue() {
        if(from == -1 || to == -1){
            return random.nextInt();
        }
        return from + random.nextInt(to);
    }
}