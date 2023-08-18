package world.entity;

import world.property.PropertyDefinition;

import java.util.List;

public class EntityDefinition {
    public String name;
    public Integer populationCount;
    public List<PropertyDefinition> properties;

    public EntityDefinition(String name, Integer populationCount, List<PropertyDefinition> properties) {
        this.name = name;
        this.populationCount = populationCount;
        this.properties = properties;
    }
}
