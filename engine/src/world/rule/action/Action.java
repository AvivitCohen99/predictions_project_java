package world.rule.action;

import world.World;
import world.entity.IEntity;

public interface Action {
    ActionType getActionType();
    String getEntityToEffect();
    void invokeAction(World world, IEntity entity) throws Exception;
}