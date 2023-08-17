package world.entity;

import world.property.PropertyDetails;
import java.util.List;

public class EntityDetails {
    public final String name;
    public final int population;
    public final List<PropertyDetails> propertiesDetails;

    public EntityDetails(String name, int population, List<PropertyDetails> propertiesDetails) {
        this.name = name;
        this.population = population;
        this.propertiesDetails = propertiesDetails;
    }
}
