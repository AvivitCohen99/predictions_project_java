package world;

import world.entity.EntityDetails;
import world.entity.IEntity;
import world.environment.IEnvironment;
import world.rule.IRule;
import world.rule.RuleDetails;
import world.termination.Termination;
import world.termination.TerminationDetails;

import java.util.List;
import java.util.stream.Collectors;

public class World {
    IEnvironment env;
    List<IEntity> entities;
    List<IRule> rules;
    Termination termination;

    public IEnvironment getEnv() {
        return env;
    }

    public World(IEnvironment env, List<IEntity> entities, List<IRule> rules, Termination termination){
        this.env = env;
        this.rules = rules;
        this.entities = entities;
        this.termination = termination;
    }

    public WorldDetails getDetails() {
        List<EntityDetails> entityDetailsList = entities.stream().map(entity -> entity.getDetails()).collect(Collectors.toList());
        List<RuleDetails> ruleDetailsList = rules.stream().map(rule -> rule.getDetails()).collect(Collectors.toList());
        TerminationDetails terminationDetails = termination.getDetails();
        return new WorldDetails(entityDetailsList, ruleDetailsList, terminationDetails);
    }

    public List<IEntity> getEntities() {
        return entities;
    }

    public List<IRule> getRules() {
        return rules;
    }

    public Termination getTermination() {
        return termination;
    }
}
