package world.rule.action;

import world.World;
import world.entity.IEntity;

import java.util.List;

public class KillAction extends AbstractAction {
    public KillAction(String entityDefinition) {
        super(ActionType.KILL, entityDefinition);
    }

    @Override
    public void invokeAction(World world, IEntity entity) throws Exception {
        entity.kill();
        world.getWorldStatistics().GetCurrent().killActionCalled++;
    }
}