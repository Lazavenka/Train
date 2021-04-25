package domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static data.CarriageSamples.anyCoach;
import static data.PersonSamples.anyValidPerson;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class PersonTest {

    @Test
    void setTicket() {
        Coach coach = anyCoach(10);
        Person person = anyValidPerson(10);

        final int seatNumber = 9;
        final UUID seatID = coach.getSeatID(seatNumber);
        person.setTicket(coach.reserveSeat(seatNumber).get());

        assertThat(person.getTicket(), is(equalTo(seatID)));
    }

}