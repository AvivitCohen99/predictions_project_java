package world.property;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import world.property.generator.api.ValueGenerator;
import world.property.generator.fixed.FixedValueGenerator;
import world.property.generator.random.impl.numeric.RandomFloatGenerator;
import world.property.generator.random.impl.numeric.RandomIntegerGenerator;

public class FloatProperty extends AbstractProperty<Double> {

    public static FloatProperty parse(Element propertyElement, String name) {
        Element propValueElement = (Element) propertyElement.getElementsByTagName("PRD-value").item(0);

        ValueGenerator generator;

        Boolean isRandom = propValueElement == null || Boolean.parseBoolean(propValueElement.getAttribute("random-initialize"));
        if (!isRandom) {
            Double init = Double.parseDouble(propValueElement.getAttribute("init"));
            generator = new FixedValueGenerator(init);
        } else {
            Node rangeNode = propertyElement.getElementsByTagName("PRD-range").item(0);
            if(rangeNode != null) {
                NamedNodeMap rangeAttributes = rangeNode.getAttributes();
                Double from = Double.parseDouble(rangeAttributes.getNamedItem("from").getNodeValue());
                Double to = Double.parseDouble(rangeAttributes.getNamedItem("to").getNodeValue());
                generator = new RandomFloatGenerator(from, to);
            }
            else {
                generator = new RandomIntegerGenerator(-1, -1);
            }
        }
        return new FloatProperty(name, generator);
    }

    public FloatProperty(String name, ValueGenerator generator) { super(name, PropertyType.FLOAT, generator); }
}
