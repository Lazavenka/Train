package domain;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class Person {
    private final LocalDate birthDate;
    private final String firstName;
    private final String lastName;

    private boolean driverLicense;
    private UUID ticket;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
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

    public LocalDate getBirthDate(){
        return birthDate;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", birthday - " + birthDate;
    }
}
