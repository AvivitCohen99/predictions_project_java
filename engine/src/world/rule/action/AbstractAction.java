package world.rule.action;

public abstract class AbstractAction implements Action{

    private final ActionType actionType;
    private final String entityName;

    protected AbstractAction(ActionType actionType, String entityName) {
        this.actionType = actionType;
        this.entityName = entityName;
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }

    @Override
    public String getEntityToEffect() {
        return entityName;
    }
}