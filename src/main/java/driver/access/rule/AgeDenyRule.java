package driver.access.rule;

import domain.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeDenyRule implements DriverDenyRule {
    @Override
    public boolean isDeny(Person person) {
        return person.getBirthDate().plus(18, ChronoUnit.YEARS).isAfter(LocalDate.now());
    }
}
