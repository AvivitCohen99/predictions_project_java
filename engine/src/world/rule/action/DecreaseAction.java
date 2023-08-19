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

public class DecreaseAction extends AbstractAction {

    private final String property;
    private final Expression byExpression;

    public static DecreaseAction parse(Element actionElement, List<EntityDefinition> entities) throws ParseException {
        if (AbstractAction.isValidNumericAction(actionElement, entities)) {
            String entityToEffect = actionElement.getAttribute("entity");
            String propertyToEffect = actionElement.getAttribute("property");
            String effectByExpression = actionElement.getAttribute("by");
            return new DecreaseAction(entityToEffect, propertyToEffect, effectByExpression);
        }
        else {
            throw new ParseException("Action exception - entity not found.");
        }
    }

    public DecreaseAction(String entityToEffect, String property, String byExpression) {
        super(ActionType.DECREASE, entityToEffect);
        this.property = property;
        this.byExpression = new Expression(byExpression, entityToEffect);
    }

    @Override
    public void invokeAction(World world, IEntity entity) throws Exception {
        PropertyDefinition prop = this.getPropertyToEffect(entity, this.property);
        Double currentValue = PropertyType.FLOAT.convert(prop.getValue());
        Double increaseBy = PropertyType.FLOAT.convert(byExpression.getValue(world));
        prop.setValue(currentValue - increaseBy);
        world.getWorldStatistics().GetCurrent().IncreaseActionCalled("decrease__" + property);
    }
}
