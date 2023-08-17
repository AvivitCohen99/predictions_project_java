package world.rule.action;

import org.w3c.dom.Element;
import world.ParseException;
import world.entity.IEntity;

import java.util.List;

public class IncreaseAction extends AbstractAction {

    private final String property;
    private final String byExpression;

    public static IncreaseAction parse(Element actionElement, List<IEntity> entities) throws ParseException {
        if (AbstractAction.isValidAction(actionElement, entities)) {
            String entityToEffect = actionElement.getAttribute("entity");
            String propertyToEffect = actionElement.getAttribute("property");
            String effectByExpression = actionElement.getAttribute("by");
            return new IncreaseAction(entityToEffect, propertyToEffect, effectByExpression);
        }
        else {
            throw new ParseException("Action exception - entity not found.");
        }
    }

    public IncreaseAction(String entityToEffect, String property, String byExpression) {
        super(ActionType.INCREASE, entityToEffect);
        this.property = property;
        this.byExpression = byExpression;
    }
}
