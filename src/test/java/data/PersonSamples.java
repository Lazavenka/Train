package data;

import domain.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PersonSamples {
    public static Person anyValidPerson(LocalDate birthDay) {
        return new Person("Alex", "Smith", birthDay);
    }

    public static Person anyValidDriver() {
        final Person person = anyValidPerson(LocalDate.of(1990, 6, 4));
        person.setDriverLicense(true);
        return person;
    }
    public static Person anyAnotherValidDriver(){
        final Person person = new Person("Tadeush", "Kostushka", LocalDate.of(1999, 8, 19));
        person.setDriverLicense(true);
        return person;
    }

    public static Person anyValidPerson(int years){
        LocalDate birthDay = LocalDate.now().minus(years, ChronoUnit.YEARS);
        return new Person("Alex", "Smith", birthDay);
    }
}
