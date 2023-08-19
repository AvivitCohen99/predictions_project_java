package world.rule.action.condition;

import org.w3c.dom.Element;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;

import java.util.List;
import java.util.Optional;

public abstract class Condition {
    protected static boolean isValidCondition(Element conditionElement, List<EntityDefinition> entities) {
        String entityToEffect = conditionElement.getAttribute("entity");
        Optional<EntityDefinition> entity = entities.stream().filter(innerEntity -> innerEntity.name.equals(entityToEffect)).findFirst();
        if (entity.isPresent()) {
            String propertyToEffect = conditionElement.getAttribute("property");
            boolean propertyExists = entity.get().properties.stream().anyMatch(innerProperty -> innerProperty.getName().equals(propertyToEffect));
            if (propertyExists) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean isFulfilled(World world, IEntity entity) throws Exception;
}
