package world.rule.action.condition;

import org.w3c.dom.Element;
import world.ParseException;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.expression.Expression;

import java.util.List;

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
        public boolean isFulfilled() {
                return false;
        }
}
