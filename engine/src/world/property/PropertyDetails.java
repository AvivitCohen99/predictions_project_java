package world.property;

import java.util.Optional;

public class PropertyDetails {
    public String name;
    public PropertyType type;
    public Optional<Number> from;
    public Optional<Number> to;
    public boolean isRandom;

    public PropertyDetails(String name, PropertyType type, Optional<Number> from, Optional<Number> to, boolean isRandom) {
        this.name = name;
        this.type = type;
        this.from = from;
        this.to = to;
        this.isRandom = isRandom;
    }
}
