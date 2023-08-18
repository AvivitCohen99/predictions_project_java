package world.property;

import org.w3c.dom.Element;

public class PropertyParser {
    public static PropertyDefinition parse(Element propertyElement) {
        String type = propertyElement.getAttribute("type");
        String name = propertyElement.getElementsByTagName("PRD-name").item(0).getTextContent();

        switch(type){
            case "decimal": return IntegerProperty.parse(propertyElement, name);
            case "float": return FloatProperty.parse(propertyElement, name);
            case "string": return StringProperty.parse(propertyElement, name);
            case "boolean": return BooleanProperty.parse(propertyElement, name);
        }
        throw new RuntimeException("unknown property type!");
    }
}
