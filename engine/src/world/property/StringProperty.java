package world.property;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import world.property.generator.api.ValueGenerator;
import world.property.generator.fixed.FixedValueGenerator;
import world.property.generator.random.impl.numeric.RandomIntegerGenerator;
import world.property.generator.random.impl.string.RandomStringValueGenerator;

public class StringProperty extends AbstractProperty<String>{

    public static StringProperty parse(Element propertyElement, String name) {
        Element propValueElement = (Element) propertyElement.getElementsByTagName("PRD-value").item(0);
        ValueGenerator generator;
        Boolean isRandom = propValueElement == null || Boolean.parseBoolean(propValueElement.getAttribute("random-initialize"));
        if (!isRandom) {
            String init = propValueElement.getAttribute("init");
            generator = new FixedValueGenerator(init);
        } else {
            generator = new RandomStringValueGenerator();
        }
        return new StringProperty(name, generator);
    }

    public StringProperty(String name, ValueGenerator valueGenerator){
        super(name, PropertyType.STRING, valueGenerator);
    }

    @Override
    public PropertyDefinition<String> getCopy() {
        return new StringProperty(name, valueGenerator);
    }
}
