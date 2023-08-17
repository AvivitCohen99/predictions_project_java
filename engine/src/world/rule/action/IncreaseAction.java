package world.rule.action;

import org.w3c.dom.Element;

public class IncreaseAction extends AbstractAction {

    private final String property;
    private final String byExpression;

    public static IncreaseAction parse(Element actionElement) {
        String entityToEffect = actionElement.getAttribute("entity");
        String propertyToEffect = actionElement.getAttribute("property");
        String effectByExpression = actionElement.getAttribute("by");
        return new IncreaseAction(entityToEffect, propertyToEffect, effectByExpression);
    }

    public IncreaseAction(String entityToEffect, String property, String byExpression) {
        super(ActionType.INCREASE, entityToEffect);
        this.property = property;
        this.byExpression = byExpression;
    }
}
