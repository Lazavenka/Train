package driver.access.rule;

import domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccessDecisionMaker {
    private static Logger logger = LoggerFactory.getLogger(AccessDecisionMaker.class);
    private final List<DriverDenyRule> rules;

    public AccessDecisionMaker(List<DriverDenyRule> rules) {
        this.rules = rules;
    }

    public boolean hasAccess(Person person) {
        for (DriverDenyRule rule : rules) {
            boolean result = rule.isDeny(person);
            if (result) {
                logger.debug("Person - " + person);
                logger.debug("Rule " + rule.getClass().getSimpleName() + " result - denied!");
                return false;
            }
        }
        return true;
    }
}
