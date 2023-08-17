package world.rule.action;

import org.w3c.dom.Element;

public class CalculateAction extends AbstractAction {

    private String resultProperty;
    private CalculationAction calculationAction;

    public static CalculateAction parse(Element actionElement){
        String entityToEffect = actionElement.getAttribute("entity");
        String resultProperty = actionElement.getAttribute("result-prop");
        CalculationAction calculationAction;
        Element multiplyElement = (Element) actionElement.getElementsByTagName("PRD-multiply");
        if(multiplyElement != null){
            calculationAction = CalculateAction.CalculationActionParse("multiply", multiplyElement);
        } else {
            Element divideElement = (Element) actionElement.getElementsByTagName("PRD-divide");
            if(divideElement != null){
                calculationAction = CalculateAction.CalculationActionParse("divide", multiplyElement);
            }
            else {
                throw new RuntimeException("calculation without action");
            }
        }

        return new CalculateAction(entityToEffect, resultProperty, calculationAction);
    }
    private static CalculationAction CalculationActionParse(String type, Element actionElement){
        String arg1 = actionElement.getAttribute("arg1");
        String arg2 = actionElement.getAttribute("arg2");
        return new CalculationAction(type, arg1, arg2);
    }

    public CalculateAction(String entityToEffect, String resultProperty, CalculationAction calculationAction) {
        super(ActionType.CALCULATE, entityToEffect);
        this.resultProperty = resultProperty;
        this.calculationAction = calculationAction;
    }

    private static class CalculationAction {
        String type;
        String arg1;
        String arg2;

        public CalculationAction(String type, String arg1, String arg2){
            this.type = type;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }
    }
}
