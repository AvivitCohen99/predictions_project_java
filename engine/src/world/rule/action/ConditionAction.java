package world.rule.action;

import org.w3c.dom.Element;
import world.ParseException;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.rule.action.condition.Condition;
import world.rule.action.condition.ConditionParser;

import java.util.List;
import java.util.Optional;

public class ConditionAction extends AbstractAction {
    Condition condition;
    Action thenAction;
    Action elseAction;

    protected static boolean isValidConditionAction(Element actionElement, List<EntityDefinition> entities) {
        String entityToEffect = actionElement.getAttribute("entity");
        Optional<EntityDefinition> entity = entities.stream().filter(innerEntity -> innerEntity.name.equals(entityToEffect)).findFirst();
        return entity.isPresent();
    }

    public static ConditionAction parse(Element actionElement, List<EntityDefinition> entities) throws ParseException {
        if (ConditionAction.isValidConditionAction(actionElement, entities)) {
            String entityToEffect = actionElement.getAttribute("entity");
            Element conditionElement = (Element) actionElement.getElementsByTagName("PRD-condition").item(0);
            Element thenElement = (Element) actionElement.getElementsByTagName("PRD-then").item(0);
            Element elseElement = (Element) actionElement.getElementsByTagName("PRD-else").item(0);

            Condition condition = ConditionParser.parse(conditionElement, entities);
            Action thenAction = ActionParser.parse((Element) thenElement.getElementsByTagName("PRD-action").item(0), entities);
            Action elseAction = elseElement != null ? ActionParser.parse((Element) elseElement.getElementsByTagName("PRD-action").item(0), entities) : null;
            return new ConditionAction(ActionType.CONDITION, entityToEffect, condition, thenAction, elseAction);
        }
        else {
            throw new ParseException("Action exception - entity not found.");
        }
    }

    protected ConditionAction(ActionType actionType, String entityToEffect, Condition condition, Action thenAction, Action elseAction) {
        super(actionType, entityToEffect);
        this.condition = condition;
        this.thenAction = thenAction;
        this.elseAction = elseAction;
    }

    @Override
    public void invokeAction(World world, IEntity entity) throws Exception {
        if (this.condition.isFulfilled(world, entity)) {
            this.thenAction.invokeAction(world, entity);
        } else if (this.elseAction != null) {
            this.elseAction.invokeAction(world, entity);
        }
    }
}
