package world.rule.action;

public interface Action {
    ActionType getActionType();
    String getEntityToEffect();
}