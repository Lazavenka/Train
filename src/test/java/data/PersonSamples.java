package data;

import domain.Age;
import domain.Person;

public class PersonSamples {
    public static Person anyValidPerson(int age){
        return new Person(Age.of(age), "Alex", "Smith");
    }

    public static Person anyValidDriver(){
        final Person person = anyValidPerson(25);
        person.setDriverLicense(true);
        return person;
    }
}
