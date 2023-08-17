package world.rule.action.condition;

import org.w3c.dom.Element;
import world.entity.IEntity;

import java.util.List;

public class ConditionParser {

    public static Condition parse(Element conditionElement, List<IEntity> entities) {
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
