package world.entity;

import world.property.Property;

import java.util.List;

public interface IEntity {
    String getName();
    int getPopulation();
    List<Property> getProps();
    void addEntityProperty(Property propertyDefinition);
    EntityDetails getDetails();
}
