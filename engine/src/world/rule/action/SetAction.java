package world.rule.action;

import org.w3c.dom.Element;
import world.ParseException;
import world.entity.IEntity;

import java.util.List;

public class SetAction extends AbstractAction {

    String propertyName;
    String value;

    public static SetAction parse(Element actionElement, List<IEntity> entities) throws ParseException {
        if (AbstractAction.isValidAction(actionElement, entities)) {
            String entityToEffect = actionElement.getAttribute("entity");
            String propertyToEffect = actionElement.getAttribute("property");
            String value = actionElement.getAttribute("value");
            return new SetAction(entityToEffect, propertyToEffect, value);
        }
        else {
            throw new ParseException("Action exception - entity not found.");
        }
    }

    public SetAction(String entityToEffect, String propertyName, String value) {
        super(ActionType.SET, entityToEffect);
        this.propertyName = propertyName;
        this.value = value;
    }
}
