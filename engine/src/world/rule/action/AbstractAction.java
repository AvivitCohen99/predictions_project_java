package world.rule.action;

import org.w3c.dom.Element;
import world.World;
import world.entity.IEntity;
import world.property.Property;

import java.util.List;
import java.util.Optional;

public abstract class AbstractAction implements Action{

    private final ActionType actionType;
    private final String entityName;

    protected AbstractAction(ActionType actionType, String entityName) {
        this.actionType = actionType;
        this.entityName = entityName;
    }

    protected static boolean isValidAction(Element actionElement, List<IEntity> entities) {
        String entityToEffect = actionElement.getAttribute("entity");
        Optional<IEntity> entity = entities.stream().filter(innerEntity -> innerEntity.getName().equals(entityToEffect)).findFirst();
        if (entity.isPresent()) {
            String propertyToEffect = actionElement.getAttribute("property");
            boolean propertyExists = entity.get().getProps().stream().anyMatch(innerProperty -> innerProperty.getName().equals(propertyToEffect));
            if (propertyExists) {
                return true;
            }
        }
        return false;
    }

    protected IEntity getEntityToEffect(World world) throws Exception {
        Optional<IEntity> entityToEffect = world.getEntities().stream().filter(entity -> entity.getName().equals(this.getEntityToEffect())).findFirst();
        if(entityToEffect.isPresent()){
            return entityToEffect.get();
        }

        throw new Exception("");
    }

    protected Property getPropertyToEffect(IEntity entity, String propertyName) throws Exception {
        Optional<Property> prop = entity.getProps().stream().filter(property-> property.getName().equals(propertyName)).findFirst();
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