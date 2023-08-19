package world.rule.action;

import org.w3c.dom.Element;
import world.ParseException;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.expression.Expression;
import world.property.PropertyDefinition;

import java.util.List;

public class SetAction extends AbstractAction {

    String propertyName;
    Expression expression;

    public static SetAction parse(Element actionElement, List<EntityDefinition> entities) throws ParseException {
        if (AbstractAction.isValidAction(actionElement, entities)) {
            String entityToEffect = actionElement.getAttribute("entity");
            String propertyToEffect = actionElement.getAttribute("property");
            String value = actionElement.getAttribute("value");
            return new SetAction(entityToEffect, propertyToEffect, value);
        }
        else {
            throw new ParseException("Action exception - entity not found.");
        }
    }

    public SetAction(String entityToEffect, String propertyName, String value) {
        super(ActionType.SET, entityToEffect);
        this.propertyName = propertyName;
        this.expression = new Expression(value, entityToEffect);
    }

    @Override
    public void invokeAction(World world) throws Exception {
        List<IEntity> entitiesToEffect = this.getEntitiesToEffect(world);
        for (IEntity entityToEffect : entitiesToEffect) {
            PropertyDefinition prop = this.getPropertyToEffect(entityToEffect, this.propertyName);
            Object currentValue = prop.getType().convert(expression.getValue(world));
            prop.setValue(currentValue);
        }
    }
}
