package world.entity;

import world.property.Property;

import java.util.List;
import java.util.Optional;

public interface IEntity {
    String getName();
    int getPopulation();
    List<Property> getProps();
    Optional<Property> getProp(String propName);

    void setPopulation(int population);

    void addEntityProperty(Property propertyDefinition);
    EntityDetails getDetails();
}
