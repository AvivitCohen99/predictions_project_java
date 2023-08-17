package world.rule.action;

import org.w3c.dom.Element;
import world.ParseException;
import world.entity.IEntity;

import java.util.List;

public class ActionParser {
    public static Action parse(Element actionElement, List<IEntity> entities) throws ParseException {
        String actionType = actionElement.getAttribute("type");
        switch (actionType) {
            case "increase":
                return IncreaseAction.parse(actionElement, entities);
            case "decrease":
                return DecreaseAction.parse(actionElement, entities);
            case "kill":
                String entityName = actionElement.getAttribute("entity");
                boolean exists = entities.stream().anyMatch(entity -> entity.getName().equals(entityName));
                if (exists) {
                    return new KillAction(entityName);
                } else {
                    throw new ParseException("Action exception - entity not found.");
                }
            case "condition":
                return ConditionAction.parse(actionElement, entities);
            case "calculation":
                return CalculateAction.parse(actionElement, entities);
            case "set":
                return SetAction.parse(actionElement, entities);
        }
        throw new RuntimeException("Unknown action");
    }
}
