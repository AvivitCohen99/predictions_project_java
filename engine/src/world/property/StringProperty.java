package world.property;

import world.property.generator.api.ValueGenerator;

public class StringProperty extends AbstractProperty<String>{
    public StringProperty(String name, ValueGenerator valueGenerator){
        super(name, PropertyType.STRING, valueGenerator);
    }
}
