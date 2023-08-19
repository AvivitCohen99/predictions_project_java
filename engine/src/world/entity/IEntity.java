package world.entity;

import world.property.PropertyDefinition;

import java.util.List;
import java.util.Optional;

public interface IEntity {
    String getName();
    List<PropertyDefinition> getProps();
    Optional<PropertyDefinition> getProp(String propName);

    void addEntityProperty(PropertyDefinition propertyDefinition);
    EntityDetails getDetails();
    boolean getIsDead();
    void kill();
}
