package world;

import world.entity.IEntity;
import world.environment.IEnvironment;
import world.rule.IRule;
import world.termination.Termination;

import java.util.List;

public class World {
    IEnvironment env;
    List<IEntity> entities;
    List<IRule> rules;
    Termination termination;

    public World(IEnvironment env, List<IEntity> entities, List<IRule> rules, Termination termination){
        this.env = env;
        this.rules = rules;
        this.entities = entities;
        this.termination = termination;
    }

    //getWorldDetails();
}
