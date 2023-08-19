package world.property;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import world.property.generator.api.ValueGenerator;
import world.property.generator.fixed.FixedValueGenerator;
import world.property.generator.random.impl.numeric.RandomIntegerGenerator;

public class IntegerProperty extends AbstractProperty<Integer> {
    public static IntegerProperty parse(Element propertyElement, String name) {
        Element propValueElement = (Element) propertyElement.getElementsByTagName("PRD-value").item(0);

        ValueGenerator generator;

        Boolean isRandom = propValueElement == null || Boolean.parseBoolean(propValueElement.getAttribute("random-initialize"));
        if (!isRandom) {
            Integer init = Integer.parseInt(propValueElement.getAttribute("init"));
            generator = new FixedValueGenerator(init);
        } else {
            Node rangeNode = propertyElement.getElementsByTagName("PRD-range").item(0);
            if(rangeNode != null) {
                NamedNodeMap rangeAttributes = rangeNode.getAttributes();
                Integer from = Integer.parseInt(rangeAttributes.getNamedItem("from").getNodeValue());
                Integer to = Integer.parseInt(rangeAttributes.getNamedItem("to").getNodeValue());
                generator = new RandomIntegerGenerator(from, to);
            }
            else {
                generator = new RandomIntegerGenerator(-1, -1);
            }
        }
        return new IntegerProperty(name, generator);
    }


    public IntegerProperty(String name, ValueGenerator generator){
        super(name, PropertyType.DECIMAL, generator);
    }


    @Override
    public PropertyDefinition<Integer> getCopy() {
        return new IntegerProperty(name, valueGenerator);
    }
}
