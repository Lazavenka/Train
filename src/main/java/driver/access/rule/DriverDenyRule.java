package driver.access.rule;

import domain.Person;

public interface DriverDenyRule {
    boolean isDeny(Person person);
}
