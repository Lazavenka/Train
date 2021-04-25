package domain.service;

import domain.CarriageType;
import domain.Person;
import domain.SimpleCarriageFactory;
import domain.Train;
import org.junit.jupiter.api.Test;

import static data.PersonSamples.anyValidPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TicketSellerImplTest {
    private final SimpleCarriageFactory factory = new SimpleCarriageFactory();
    @Test
    void sellTicket() {
        Train train = new Train("332B");
        train.addCarriage(CarriageType.COACH, factory); // generate carriage with 20 seats
        TicketSellerImpl seller = new TicketSellerImpl();
        Person person = anyValidPerson(25);

        boolean success = seller.sellTicket(train, 1, 15, person);

        assertThat(success, is(true));
    }
}