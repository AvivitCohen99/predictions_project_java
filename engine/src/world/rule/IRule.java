package world.rule;

import world.World;
import world.rule.action.Action;
import world.rule.activation.IActivation;

import java.util.List;

public interface IRule {
    String getName();
    IActivation getActivation();
    List<Action> getActionsToPerform(int ticks);
    void addAction(Action action);
    RuleDetails getDetails();
    void invokeRule(World world) throws Exception;
}
