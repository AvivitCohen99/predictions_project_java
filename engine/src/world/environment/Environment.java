package world.environment;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import world.property.Property;
import world.property.PropertyParser;

import java.util.HashMap;
import java.util.Map;

public class Environment implements IEnvironment{
    private final Map<String, Property> propNameToProp;

    public static Environment environmentParser(Element environmentElement){
        Environment env = new Environment();
        NodeList envPropertyList = environmentElement.getElementsByTagName("PRD-env-property");
        for (int i = 0; i < envPropertyList.getLength(); i++) {
            Node envPropertyNode = envPropertyList.item(i);
            if (envPropertyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element envPropertyElement = (Element) envPropertyNode;
                env.addEnvironmentVariable(PropertyParser.parse(envPropertyElement));
            }
        }
        return env;
    }

    public Environment() {
        this.propNameToProp = new HashMap<>();
    }

    public Property getEnvironmentVariable(String name){
        return propNameToProp.get(name);
    }

    public void addEnvironmentVariable(Property property){
        propNameToProp.put(property.getName(), property);
    }
}
