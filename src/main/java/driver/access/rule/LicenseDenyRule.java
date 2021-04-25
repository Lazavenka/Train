package driver.access.rule;

import domain.Person;

public class LicenseDenyRule implements DriverDenyRule {

    @Override
    public boolean isDeny(Person person) {
        return !person.isDriverLicense();
    }
}
