package world.rule.action;

import org.w3c.dom.Element;

public class DecreaseAction extends AbstractAction {

    private final String property;
    private final String byExpression;

    public static DecreaseAction parse(Element actionElement) {
        String entityToEffect = actionElement.getAttribute("entity");
        String propertyToEffect = actionElement.getAttribute("property");
        String effectByExpression = actionElement.getAttribute("by");
        return new DecreaseAction(entityToEffect, propertyToEffect, effectByExpression);
    }

    public DecreaseAction(String entityToEffect, String property, String byExpression) {
        super(ActionType.DECREASE, entityToEffect);
        this.property = property;
        this.byExpression = byExpression;
    }
}
