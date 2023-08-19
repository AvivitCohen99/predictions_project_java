package world.property;

import org.w3c.dom.Element;
import world.property.generator.api.ValueGenerator;
import world.property.generator.fixed.FixedValueGenerator;
import world.property.generator.random.impl.bool.RandomBooleanValueGenerator;

public class BooleanProperty extends AbstractProperty<Boolean> {

    public static BooleanProperty parse(Element booleanPropertyElement, String name) {
        Element propValueElement = (Element) booleanPropertyElement.getElementsByTagName("PRD-value").item(0);
        ValueGenerator generator;
        Boolean isRandom = propValueElement == null || Boolean.parseBoolean(propValueElement.getAttribute("random-initialize"));
        if (!isRandom) {
            Boolean init = Boolean.parseBoolean(propValueElement.getAttribute("init"));
            generator = new FixedValueGenerator(init);
        } else {
            generator = new RandomBooleanValueGenerator();
        }
        return new BooleanProperty(name, generator);
    }

    public BooleanProperty(String name, ValueGenerator generator) {
        super(name, PropertyType.BOOLEAN, generator);
    }

    @Override
    public PropertyDefinition<Boolean> getCopy() {
        return new BooleanProperty(name, valueGenerator);
    }
}
