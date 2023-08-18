package world;

import world.entity.EntityDefinition;
import world.rule.RuleDetails;
import world.termination.TerminationDetails;

import java.util.List;

public class WorldDefinition {
    public List<EntityDefinition> entityDefinitions;
    public List<RuleDetails> rulesDetails;
    public TerminationDetails terminationDetails;

    public WorldDefinition(List<EntityDefinition> entityDetails, List<RuleDetails> rulesDetails, TerminationDetails terminationDetails) {
        this.entityDefinitions = entityDetails;
        this.rulesDetails = rulesDetails;
        this.terminationDetails = terminationDetails;
    }
}
