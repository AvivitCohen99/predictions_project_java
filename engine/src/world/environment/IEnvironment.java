package world.environment;

import world.property.Property;

public interface IEnvironment {
    public Property getEnvironmentVariable(String name);
    public void addEnvironmentVariable(Property property);
}
