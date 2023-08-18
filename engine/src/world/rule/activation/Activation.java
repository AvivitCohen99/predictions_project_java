package world.rule.activation;

import java.util.Random;

public class Activation implements IActivation {
    private double probability;
    private int ticksCycle;
    private Random random;

    public Activation() {
        this(1,1);
    }

    public Activation(int ticksCycle) {
       this(1, ticksCycle);
    }

    public Activation(double probability) {
        this(probability, 1);
    }

    public Activation(double probability, int ticksCycle) {
        this.probability = probability;
        this.ticksCycle = ticksCycle;
        random = new Random();
    }

    @Override
    public boolean isActive(int currentTick) {
        return currentTick % this.ticksCycle == 0 && random.nextFloat() < this.probability;
    }

    @Override
    public ActivationDetails getDetails() {
        return new ActivationDetails(ticksCycle, probability);
    }
}
