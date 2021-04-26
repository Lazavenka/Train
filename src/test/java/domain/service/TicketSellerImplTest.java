package domain.service;

import domain.Person;
import domain.Train;
import org.junit.jupiter.api.Test;

import static data.PersonSamples.anyValidPerson;
import static data.TrainSamples.anyCorrectPassengerTrain;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TicketSellerImplTest {

    private final TicketSeller ticketSeller = new TicketSellerImpl();

    @Test
    void sellTicket() {
        Train train = anyCorrectPassengerTrain(); // generate train with 2 coach of 5 seats each

        Person person = anyValidPerson(25);
        assertThat(person.getTicket(), is(nullValue()));

        boolean success = ticketSeller.sellTicket(train, 2, 3, person);

        assertThat(success, is(true));
        assertThat(person.getTicket(), is(not(nullValue())));
    }

    @Test
    void sellTicket_incorrectTicket() {
        Train train = anyCorrectPassengerTrain(); // generate carriage with 20 seats

        Person person = anyValidPerson(25);

        boolean success = ticketSeller.sellTicket(train, 2, 55, person);

        assertThat(success, is(false));
        assertThat(person.getTicket(), is(nullValue()));
    }

}