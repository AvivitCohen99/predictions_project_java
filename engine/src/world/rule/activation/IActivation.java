package world.rule.activation;

public interface IActivation {
    boolean isActive(int tickNumber);
    ActivationDetails getDetails();
}
