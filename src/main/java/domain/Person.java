package domain;

import java.util.UUID;

public class Person {
    private final Age age;
    private final String firstName;
    private final String lastName;

    private boolean driverLicense;
    private UUID ticket;

    public Person(Age age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return age.intValue();
    }

    public String getName() {
        return firstName;
    }

    public UUID getTicket() {
        return ticket;
    }

    public void setTicket(UUID ticket) {
        this.ticket = ticket;
    }

    public void setDriverLicense(boolean license) {
        this.driverLicense = license;
    }

    public boolean isDriverLicense() {
        return driverLicense;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", age - " + age;
    }
}
