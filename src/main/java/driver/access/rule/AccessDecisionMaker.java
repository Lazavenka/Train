package driver.access.rule;

import domain.Person;

import java.util.List;

public class AccessDecisionMaker {
    private final List<DriverDenyRule> rules;

    public AccessDecisionMaker(List<DriverDenyRule> rules) {
        this.rules = rules;
    }
    public boolean hasAccess(Person person){
        for (DriverDenyRule rule : rules) {
            if(rule.isDeny(person)){
                return false;
            }
        }
        return true;
    }
}
