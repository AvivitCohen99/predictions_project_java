package world.rule.action.condition;

import org.w3c.dom.Element;

public class ConditionParser {

    public static Condition parse(Element conditionElement) {
        String singularity = conditionElement.getAttribute("singularity");
        if (singularity.equals("single")) {
            return SingleCondition.parse(conditionElement);
        }
        else if (singularity.equals("multiple")) {
            return MultipleCondition.parse(conditionElement);
        }
    throw new RuntimeException("could not find condition");
    }
}
