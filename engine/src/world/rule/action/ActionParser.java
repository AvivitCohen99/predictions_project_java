package world.rule.action;

import org.w3c.dom.Element;

public class ActionParser {
    public static Action parse(Element actionElement) {
        String actionType = actionElement.getAttribute("type");
        switch (actionType) {
            case "increase": return IncreaseAction.parse(actionElement);
            case "decrease": return DecreaseAction.parse(actionElement);
            case "kill":
                String entityName = actionElement.getAttribute("entity");
                return new KillAction(entityName);
            case "condition": return ConditionAction.parse(actionElement);
            case "calculation": return CalculateAction.parse(actionElement);
            case "set": return SetAction.parse(actionElement);
        }
        throw new RuntimeException("Unknown action");
    }
}
