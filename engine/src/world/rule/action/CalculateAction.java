package world.rule.action;

import org.w3c.dom.Element;
import world.ParseException;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.expression.Expression;
import world.property.PropertyDefinition;

import java.util.List;
import java.util.Optional;

public class CalculateAction extends AbstractAction {

    private String resultProperty;
    private CalculationAction calculationAction;

    protected static boolean isValidAction(Element actionElement, List<EntityDefinition> entities) {
        String entityToEffect = actionElement.getAttribute("entity");
        Optional<EntityDefinition> entity = entities.stream().filter(innerEntity -> innerEntity.name.equals(entityToEffect)).findFirst();
        if (entity.isPresent()) {
            String propertyToEffect = actionElement.getAttribute("result-prop");
            boolean propertyExists = entity.get().properties.stream().anyMatch(innerProperty -> innerProperty.getName().equals(propertyToEffect));
            if (propertyExists) {
                return true;
            }
        }
        return false;
    }

    public static CalculateAction parse(Element actionElement, List<EntityDefinition> entities) throws ParseException{
        if (CalculateAction.isValidAction(actionElement, entities)) {
            String entityToEffect = actionElement.getAttribute("entity");
            String resultProperty = actionElement.getAttribute("result-prop");
            CalculationAction calculationAction;
            Element multiplyElement = (Element) actionElement.getElementsByTagName("PRD-multiply");
            if (multiplyElement != null) {
                calculationAction = CalculateAction.CalculationActionParse("multiply", multiplyElement, entityToEffect);
            } else {
                Element divideElement = (Element) actionElement.getElementsByTagName("PRD-divide");
                if (divideElement != null) {
                    calculationAction = CalculateAction.CalculationActionParse("divide", multiplyElement, entityToEffect);
                } else {
                    throw new RuntimeException("calculation without action");
                }
            }

            return new CalculateAction(entityToEffect, resultProperty, calculationAction);
        }
        else {
            throw new ParseException("Action exception - entity not found.");
        }
    }

    private static CalculationAction CalculationActionParse(String type, Element actionElement, String entity){
        String arg1 = actionElement.getAttribute("arg1");
        String arg2 = actionElement.getAttribute("arg2");
        return new CalculationAction(type, arg1, arg2, entity);
    }

    public CalculateAction(String entityToEffect, String resultProperty, CalculationAction calculationAction) {
        super(ActionType.CALCULATE, entityToEffect);
        this.resultProperty = resultProperty;
        this.calculationAction = calculationAction;
    }

    @Override
    public void invokeAction(World world, IEntity entity) throws Exception {
        PropertyDefinition resultProp = this.getPropertyToEffect(entity, this.resultProperty);
        Object calculationResult = calculationAction.calculate(world, entity);
        resultProp.setValue(calculationResult);
    }

    private static class CalculationAction {
        String type;
        Expression arg1;
        Expression arg2;

        public CalculationAction(String type, String arg1, String arg2, String entity){
            this.type = type;
            this.arg1 = new Expression(arg1, entity);
            this.arg2 = new Expression(arg2, entity);
        }

        public Number calculate(World world, IEntity entity) throws Exception {
            Number a = this.arg1.getNumericValue(world, entity);
            Number b = this.arg2.getNumericValue(world, entity);
            if(type.equals("multiply")){
                return a.doubleValue() * b.doubleValue();
            }
            else if(type.equals("divide") && b.doubleValue() > 0){
                return a.doubleValue() / b.doubleValue();
            }

            throw new Exception("invalid numbers");
        }
    }
}
