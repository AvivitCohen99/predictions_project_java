package world.rule.action;

import org.w3c.dom.Element;
import world.ParseException;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.expression.Expression;
import world.property.PropertyDefinition;
import world.property.PropertyType;

import java.util.List;

public class IncreaseAction extends AbstractAction {

    private final String property;
    private final Expression byExpression;

    public static IncreaseAction parse(Element actionElement, List<EntityDefinition> entities) throws ParseException {
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
        this.byExpression = new Expression(byExpression, entityToEffect);
    }

    @Override
    public void invokeAction(World world) throws Exception {
        for (IEntity entityToEffect : this.getEntitiesToEffect(world)) {
            PropertyDefinition prop = this.getPropertyToEffect(entityToEffect, this.property);
            Integer currentValue = PropertyType.DECIMAL.convert(prop.getValue());
            Integer increaseBy = PropertyType.DECIMAL.convert(byExpression.getValue(world));
            prop.setValue(currentValue + increaseBy);
        }
    }
}
