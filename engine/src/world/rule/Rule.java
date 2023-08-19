package world.rule;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import world.ParseException;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.rule.action.Action;
import world.rule.action.ActionParser;
import world.rule.activation.Activation;
import world.rule.activation.IActivation;

import java.util.ArrayList;
import java.util.List;

public class Rule implements IRule {

    private final String name;
    private IActivation activation;
    private final List<Action> actions;

    public static IRule parse(Element ruleElement, List<EntityDefinition> entities) throws ParseException {
        String ruleName = ruleElement.getAttribute("name");
        IActivation activation;
        Element activationElement = (Element) ruleElement.getElementsByTagName("PRD-activation").item(0);
        if(activationElement != null) {
            String ticksAttribute = activationElement.getAttribute("ticks");
            Integer activationTicks = !ticksAttribute.isEmpty() ? Integer.parseInt(activationElement.getAttribute("ticks")) : -1;
            String probabilityAttribute = activationElement.getAttribute("probability");
            Double activationProbability = !probabilityAttribute.isEmpty() ? Double.parseDouble(activationElement.getAttribute("probability")) : -1;
            if (activationTicks > 0 && activationProbability > 0) {
                activation = new Activation(activationProbability, activationTicks);
            } else if(activationTicks > 0){
                activation = new Activation(activationTicks);
            }
            else if(activationProbability > 0){
                activation = new Activation(activationProbability);
            }
            else {
                activation = new Activation();
            }
        }
        else {
            activation = new Activation();
        }

        IRule rule = new Rule(ruleName, activation);
        NodeList childNodes = ((Element)ruleElement.getElementsByTagName("PRD-actions").item(0)).getChildNodes();
         List<Element> actionsList = new ArrayList();
        for(int i = 0; i < childNodes.getLength(); i++){
            if (childNodes.item(i) instanceof Element) {
                Element childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("PRD-action")) {
                    actionsList.add(childElement);
                }
            }
        }

        for(Element actionElement: actionsList){
            rule.addAction(ActionParser.parse(actionElement, entities));
        }

        return rule;
    }

    public Rule(String name, IActivation activation) {
        this.name = name;
        this.activation = activation;
        actions = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IActivation getActivation() {
        return activation;
    }

    @Override
    public List<Action> getActionsToPerform(int ticks) {
        return actions;
    }

    @Override
    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public RuleDetails getDetails() {
        return new RuleDetails(this.name, this.activation.getDetails(), this.actions.size());
    }

    @Override
    public void invokeRule(World world) throws Exception {
        for (IEntity entity : world.getEntities()) {
            if (entity.getIsDead()) {
                continue;
            }
            for (Action action : actions) {
                if (!action.getEntityToEffect().toLowerCase().equals(entity.getName().toLowerCase())) {
                    continue;
                }
                action.invokeAction(world, entity);
            }
        }
    }
}

