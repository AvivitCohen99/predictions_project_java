package world.entity;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import world.ParseException;
import world.property.Property;
import world.property.PropertyDetails;
import world.property.PropertyParser;

import java.util.ArrayList;
import java.util.List;

public class Entity implements IEntity {
    final String name;
    int population;
    final List<Property> properties;

    public static IEntity entityParser(Element entityElement) throws ParseException {
        String name = entityElement.getAttribute("name");
        Integer population = Integer.parseInt(entityElement.getElementsByTagName("PRD-population").item(0).getTextContent());
        Entity entityDefinition = new Entity(name, population);

        NodeList entitiesPropertiesList = entityElement.getElementsByTagName("PRD-property");
        for (int j = 0; j < entitiesPropertiesList.getLength(); j++) {
            Node entitiesPropertyNode = entitiesPropertiesList.item(j);
            if (entitiesPropertyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element entityPropertyElement = (Element) entitiesPropertyNode;
                //TODO: check if search is OK.
                Property property = PropertyParser.parse(entityPropertyElement);
                boolean exist = entityDefinition.properties.stream().anyMatch(propertyToFind -> property.getName().equals(propertyToFind.getName()));
                if (!exist) {
                    entityDefinition.addEntityProperty(property);
                }
                else {
                   throw new ParseException("entity exception - property already exists.");
                }
            }
        }
        return entityDefinition;
    }

    public Entity(String name, int population) {
        this.name = name;
        this.population = population;
        this.properties = new ArrayList();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPopulation() {
        return population;
    }

    @Override
    public List<Property> getProps() {
        return properties;
    }

    @Override
    public void addEntityProperty(Property property) {
        properties.add(property);
    }

    @Override
    public EntityDetails getDetails() {
//        List<PropertyDetails> propertyDetails = properties.stream().map(property -> property.getDetails());
//        return new EntityDetails(name, population, propertyDetails);
        return null; //TODO: fix!
    }
}
