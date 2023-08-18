package world.environment;

import world.property.Property;

import java.util.List;

public interface IEnvironment {
    Property getEnvironmentVariable(String name);
    void addEnvironmentVariable(Property property);
    List<Property> getAllProperties();
}
