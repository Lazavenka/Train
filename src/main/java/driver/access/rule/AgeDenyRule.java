package driver.access.rule;

import domain.Person;

public class AgeDenyRule implements DriverDenyRule {
    @Override
    public boolean isDeny(Person person) {
        return person.getAge() >= 18;
    }
}
