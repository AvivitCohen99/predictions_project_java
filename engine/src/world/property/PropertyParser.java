package world.property;

import org.w3c.dom.Element;

public class PropertyParser {
    public static Property parse(Element propertyElement) {
        String type = propertyElement.getAttribute("type");
        String name = propertyElement.getElementsByTagName("PRD-name").item(0).getTextContent();

        if (type.equals("decimal")) {
            return IntegerProperty.parse(propertyElement, name);
        }

        throw new RuntimeException("need to implement!"); // TODO: need to implement boolean, string and float
    }
}
