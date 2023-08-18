package world.rule;

import world.rule.activation.ActivationDetails;

import java.util.List;

public class RuleDetails {
    public String name;
    public ActivationDetails activationDetails;
    public int actionsCount;

    public RuleDetails(String name, ActivationDetails activationDetails, int actionsCount) {
        this.name = name;
        this.activationDetails = activationDetails;
        this.actionsCount = actionsCount;
    }
}
