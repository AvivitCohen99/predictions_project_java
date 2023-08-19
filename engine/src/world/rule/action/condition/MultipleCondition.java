package world.rule.action.condition;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import world.ParseException;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

public class MultipleCondition extends Condition {
    String logical;
    List<Condition> innerConditions;

    public static MultipleCondition parse(Element conditionElement, List<EntityDefinition> entities) throws ParseException {
        List<Condition> innerConditions = new ArrayList();
        String logical = conditionElement.getAttribute("logical");
        NodeList innerConditionsList = conditionElement.getElementsByTagName("PRD-condition");
        for (int j = 0; j < innerConditionsList.getLength(); j++) {
            Element innerConditionElement = (Element) innerConditionsList.item(j);
            innerConditions.add(ConditionParser.parse(innerConditionElement, entities));
        }
        return new MultipleCondition(logical, innerConditions);
    }

    public MultipleCondition (String logical, List<Condition> innerConditions) {
        this.logical = logical;
        this.innerConditions = innerConditions;
    }

    @Override
    public boolean isFulfilled(World world, IEntity entity) throws Exception {
        Boolean and = Boolean.TRUE;
        Boolean or = Boolean.FALSE;
        for (Condition condition : innerConditions) {
            Boolean current = condition.isFulfilled(world, entity);
            and = and && current;
            or = or || current;
        }
        if (logical.equals("and")) {
            return and;
        } else
            return or;
    }
}
