package world.rule.action;

import org.w3c.dom.Element;
import world.rule.action.condition.Condition;
import world.rule.action.condition.ConditionParser;

public class ConditionAction extends AbstractAction {
    Condition condition;
    Action thenAction;
    Action elseAction;

    public static ConditionAction parse(Element actionElement) {
        String entityToEffect = actionElement.getAttribute("entity");
        Element conditionElement = (Element) actionElement.getElementsByTagName("PRD-condition").item(0);
        Element thenElement = (Element) actionElement.getElementsByTagName("PRD-then").item(0);
        Element elseElement = (Element) actionElement.getElementsByTagName("PRD-else").item(0);

        Condition condition = ConditionParser.parse(conditionElement);
        Action thenAction = ActionParser.parse((Element) thenElement.getElementsByTagName("PRD-action").item(0));
        Action elseAction = elseElement != null ? ActionParser.parse((Element) elseElement.getElementsByTagName("PRD-action").item(0)) : null;
        return new ConditionAction(ActionType.CONDITION, entityToEffect, condition, thenAction, elseAction);
    }

    protected ConditionAction(ActionType actionType, String entityToEffect, Condition condition, Action thenAction, Action elseAction) {
        super(actionType, entityToEffect);
        this.condition = condition;
        this.thenAction = thenAction;
        this.elseAction = elseAction;
    }
}
