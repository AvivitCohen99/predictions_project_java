package world.rule.action;

import org.w3c.dom.Element;

public class SetAction extends AbstractAction {

    String propertyName;
    String value;

    public static SetAction parse(Element actionElement){
        String entityToEffect = actionElement.getAttribute("entity");
        String propertyToEffect = actionElement.getAttribute("property");
        String value = actionElement.getAttribute("value");

        return new SetAction(entityToEffect, propertyToEffect, value);
    }

    public SetAction(String entityToEffect, String propertyName, String value) {
        super(ActionType.SET, entityToEffect);
        this.propertyName = propertyName;
        this.value = value;
    }
}
