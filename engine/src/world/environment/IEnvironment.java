package world.environment;

import world.property.PropertyDefinition;

import java.util.List;

public interface IEnvironment {
    PropertyDefinition getEnvironmentVariable(String name);
    void addEnvironmentVariable(PropertyDefinition property);
    List<PropertyDefinition> getAllProperties();
}
