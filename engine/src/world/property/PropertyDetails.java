package world.property;

import java.util.Optional;

public class PropertyDetails {
    public String name;
    public PropertyType type;
    Optional<Integer> from;
    Optional<Integer> to;
    boolean isRandom;

    public PropertyDetails(String name, PropertyType type, Optional<Integer> from, Optional<Integer> to, boolean isRandom) {
        this.name = name;
        this.type = type;
        this.from = from;
        this.to = to;
        this.isRandom = isRandom;
    }
}
