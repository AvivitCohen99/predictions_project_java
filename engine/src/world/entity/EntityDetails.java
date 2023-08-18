package world.entity;

import world.property.PropertyDetails;
import java.util.List;

public class EntityDetails {
    public final String name;
    public final List<PropertyDetails> propertiesDetails;

    public EntityDetails(String name, List<PropertyDetails> propertiesDetails) {
        this.name = name;
        this.propertiesDetails = propertiesDetails;
    }
}
