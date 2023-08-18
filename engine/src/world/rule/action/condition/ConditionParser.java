package world.rule.action.condition;

import org.w3c.dom.Element;
import world.ParseException;
import world.entity.EntityDefinition;
import world.entity.IEntity;

import java.util.List;

public class ConditionParser {

    public static Condition parse(Element conditionElement, List<EntityDefinition> entities) throws ParseException {
        String singularity = conditionElement.getAttribute("singularity");
        if (singularity.equals("single")) {
            return SingleCondition.parse(conditionElement, entities);
        }
        else if (singularity.equals("multiple")) {
            return MultipleCondition.parse(conditionElement, entities);
        }
    throw new RuntimeException("could not find condition");
    }
}
