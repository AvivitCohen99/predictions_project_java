package world.rule.action.condition;

import org.w3c.dom.Element;
import world.ParseException;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.expression.Expression;
import world.property.PropertyDefinition;
import world.property.PropertyType;

import java.util.List;
import java.util.Optional;

public class SingleCondition extends Condition {

        String entityToEffect;
        String propertyName;
        String operator;
        Expression value;

        public static SingleCondition parse(Element conditionElement, List<EntityDefinition> entities) throws ParseException {
                if (Condition.isValidCondition(conditionElement, entities)) {
                        String entityToEffect = conditionElement.getAttribute("entity");
                        String propertyName = conditionElement.getAttribute("property");
                        String operator = conditionElement.getAttribute("operator");
                        String value = conditionElement.getAttribute("value");
                        return new SingleCondition(entityToEffect, propertyName, operator, value);
                }
                throw new ParseException("condition is not valid.");
        }

        public SingleCondition(String entityToEffect, String propertyName, String operator, String value) {
                this.entityToEffect = entityToEffect;
                this.propertyName = propertyName;
                this.operator = operator;
                this.value = new Expression(value, entityToEffect);
        }


        @Override
        public boolean isFulfilled(World world, IEntity entity) throws Exception {
                if (!entity.getName().equals(entityToEffect)) {
                        return false;
                }
                Optional<PropertyDefinition> optionalPropertyToCheck = entity.getProp(propertyName);
                if (!optionalPropertyToCheck.isPresent()) {
                        System.out.println("Trying to activate condition on missing property! property_name=" + propertyName);
                        return false;
                }
                PropertyDefinition propertyToCheck = optionalPropertyToCheck.get();
                Object propValue = propertyToCheck.getValue();
                Object comparison = value.getValue(world);
                switch (operator.toLowerCase()) {
                        case "=":
                                return propValue.equals(comparison);
                        case "!=":
                                return !propValue.equals(comparison);
                        case "bt":
                                return (Double)PropertyType.FLOAT.convert(propValue) > (Double)PropertyType.FLOAT.convert(comparison);
                        case "lt":
                                return (Double)PropertyType.FLOAT.convert(propValue) < (Double)PropertyType.FLOAT.convert(comparison);
                }
                throw new Exception("Not supported comparison type " + operator);
        }
}
