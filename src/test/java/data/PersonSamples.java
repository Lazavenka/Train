package data;

import domain.Age;
import domain.Person;

public class PersonSamples {
    public static Person anyValidPerson(int age) {
        return new Person(Age.of(age), "Alex", "Smith");
    }

    public static Person anyValidDriver() {
        final Person person = anyValidPerson(25);
        person.setDriverLicense(true);
        return person;
    }
    public static Person anyAnotherValidDriver(){
        final Person person = new Person(Age.of(19), "Tadeush", "Kostushka");
        person.setDriverLicense(true);
        return person;
    }
}
