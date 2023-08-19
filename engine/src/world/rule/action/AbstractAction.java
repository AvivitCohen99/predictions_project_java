package world.rule.action;

import org.w3c.dom.Element;
import world.World;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.property.PropertyDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractAction implements Action{

    private final ActionType actionType;
    private final String entityName;

    protected AbstractAction(ActionType actionType, String entityName) {
        this.actionType = actionType;
        this.entityName = entityName;
    }

    protected static boolean isValidAction(Element actionElement, List<EntityDefinition> entities) {
        String entityToEffect = actionElement.getAttribute("entity");
        Optional<EntityDefinition> entity = entities.stream().filter(innerEntity -> innerEntity.name.equals(entityToEffect)).findFirst();
        if (entity.isPresent()) {
            String propertyToEffect = actionElement.getAttribute("property");
            boolean propertyExists = entity.get().properties.stream().anyMatch(innerProperty -> innerProperty.getName().equals(propertyToEffect));
            if (propertyExists) {
                return true;
            }
        }
        return false;
    }

    protected List<IEntity> getEntitiesToEffect(World world) throws Exception {
        List<IEntity> entityToEffect = new ArrayList();
        for (IEntity entity : world.getEntities()) {
            if (!entity.getName().equals(entityName)) {
                continue;
            }
            entityToEffect.add(entity);
        }
        return entityToEffect;
    }

    protected PropertyDefinition getPropertyToEffect(IEntity entity, String propertyName) throws Exception {
        Optional<PropertyDefinition> prop = entity.getProps().stream().filter(property-> property.getName().equals(propertyName)).findFirst();
        if(prop.isPresent()){
            return prop.get();
        }

        throw new Exception("");
    }


    @Override
    public ActionType getActionType() {
        return actionType;
    }

    @Override
    public String getEntityToEffect() {
        return entityName;
    }
}