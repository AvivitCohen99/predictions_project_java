package world;

import world.entity.Entity;
import world.entity.EntityDefinition;
import world.entity.IEntity;
import world.environment.IEnvironment;
import world.rule.IRule;
import world.rule.RuleDetails;
import world.termination.Termination;
import world.termination.TerminationDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    IEnvironment env;
    List<EntityDefinition> entityDefinitions;
    List<IEntity> entities;
    List<IRule> rules;
    Termination termination;

    WorldStatistics worldStatistics;

    public IEnvironment getEnv() {
        return env;
    }
    public WorldStatistics getWorldStatistics() {
        return worldStatistics;
    }

    public World(IEnvironment env, List<EntityDefinition> entityDefinitions, List<IRule> rules, Termination termination){
        this.env = env;
        this.rules = rules;
        this.entities = GenerateEntities(entityDefinitions);
        this.entityDefinitions = entityDefinitions;
        this.termination = termination;

        worldStatistics = new WorldStatistics();
    }

    private List<IEntity> GenerateEntities(List<EntityDefinition> entityDefinitions) {
        List<IEntity> entities = new ArrayList();
        for (EntityDefinition entityDefinition : entityDefinitions) {
            for (int currentEntityIndex = 0; currentEntityIndex < entityDefinition.populationCount; currentEntityIndex++) {
                entities.add(new Entity(entityDefinition));
            }
        }
        return entities;
    }

    public WorldDefinition getDetails() {
        List<RuleDetails> ruleDetailsList = rules.stream().map(rule -> rule.getDetails()).collect(Collectors.toList());
        TerminationDetails terminationDetails = termination.getDetails();
        return new WorldDefinition(entityDefinitions, ruleDetailsList, terminationDetails);
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
