package world.rule.action;

import world.World;
import world.entity.IEntity;

public class KillAction extends AbstractAction {
    public KillAction(String entityDefinition) {
        super(ActionType.KILL, entityDefinition);
    }

    @Override
    public void invokeAction(World world) throws Exception {
        IEntity entityToEffect = this.getEntityToEffect(world);
        int population = entityToEffect.getPopulation();
        if(population > 0) {
            entityToEffect.setPopulation(entityToEffect.getPopulation() - 1);
        }
    }
}