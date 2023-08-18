package world.rule.action;

import world.World;

public interface Action {
    ActionType getActionType();
    String getEntityToEffect();
    void invokeAction(World world) throws Exception;
}