package world.rule.action;

import world.World;
import world.entity.IEntity;

import java.util.List;

public class KillAction extends AbstractAction {
    public KillAction(String entityDefinition) {
        super(ActionType.KILL, entityDefinition);
    }

    @Override
    public void invokeAction(World world) throws Exception {
        if (world.getEntities().isEmpty()) {
//            TODO: say we have no one to kill.
            return;
        }
        List<IEntity> entitiesToEffect = this.getEntitiesToEffect(world);
        for (IEntity entityToEffect : entitiesToEffect) {
            world.getEntities().remove(entityToEffect);
        }
    }
}