package world.rule;

import world.rule.action.Action;
import world.rule.activation.Activation;

import java.util.List;

public interface IRule {
    String getName();
    Activation getActivation();
    List<Action> getActionsToPerform(int ticks);
    void addAction(Action action);
}
