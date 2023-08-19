package world.entity;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import world.ParseException;
import world.property.PropertyDefinition;
import world.property.PropertyDetails;
import world.property.PropertyParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Entity implements IEntity {
    final String name;
    final List<PropertyDefinition> properties;
    boolean isDead;

    public Entity(EntityDefinition entityDefinition) {
        name = entityDefinition.name;
        isDead = false;

        properties = new ArrayList();
        for (PropertyDefinition propertyDefinition : entityDefinition.properties) {
            PropertyDefinition newProperty = propertyDefinition.getCopy();
            newProperty.setValue(newProperty.generateValue());
            properties.add(newProperty);
        }
    }

    @Override
    public boolean getIsDead() {
        return isDead;
    }

    @Override
    public void kill() {
        isDead = true;
    }

    public static EntityDefinition entityParser(Element entityElement) throws ParseException {
        String name = entityElement.getAttribute("name");
        Integer population = Integer.parseInt(entityElement.getElementsByTagName("PRD-population").item(0).getTextContent());

        List<PropertyDefinition> propertyDefinitions = new ArrayList();

        NodeList propertiesToParseList = entityElement.getElementsByTagName("PRD-property");
        for (int j = 0; j < propertiesToParseList.getLength(); j++) {
            Node entitiesPropertyNode = propertiesToParseList.item(j);
            if (entitiesPropertyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element entityPropertyElement = (Element) entitiesPropertyNode;
                PropertyDefinition property = PropertyParser.parse(entityPropertyElement);
                boolean exist = propertyDefinitions.stream().anyMatch(propertyToFind -> property.getName().equals(propertyToFind.getName()));
                if (exist) {
                    throw new ParseException("entity exception - property already exists.");
                }
                propertyDefinitions.add(property);
            }
        }
        EntityDefinition entityDefinition = new EntityDefinition(name, population, propertyDefinitions);
        return entityDefinition;
    }

    public Entity(String name, int population) {
        this.name = name;
        this.properties = new ArrayList();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<PropertyDefinition> getProps() {
        return properties;
    }

    @Override
    public Optional<PropertyDefinition> getProp(String propName) {
        return this.properties.stream().filter(prop -> prop.getName().equals(propName)).findFirst();
    }

    @Override
    public void addEntityProperty(PropertyDefinition property) {
        properties.add(property);
    }

    @Override
    public EntityDetails getDetails() {
        List<PropertyDetails> propertyDetails = properties.stream().map(PropertyDefinition::getDetails).collect(Collectors.toList());
        return new EntityDetails(name, propertyDetails);
    }
}
