package world;

import world.entity.EntityDetails;
import world.rule.RuleDetails;
import world.termination.TerminationDetails;

import java.util.List;

public class WorldDetails {
    public List<EntityDetails> entityDetails;
    public List<RuleDetails> rulesDetails;
    public TerminationDetails terminationDetails;

    public WorldDetails(List<EntityDetails> entityDetails, List<RuleDetails> rulesDetails, TerminationDetails terminationDetails) {
        this.entityDetails = entityDetails;
        this.rulesDetails = rulesDetails;
        this.terminationDetails = terminationDetails;
    }
}
