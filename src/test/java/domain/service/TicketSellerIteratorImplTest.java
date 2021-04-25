package domain.service;

import domain.CarriageType;
import domain.Person;
import domain.SimpleCarriageFactory;
import domain.Train;
import org.junit.jupiter.api.Test;

import static data.PersonSamples.anyValidPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TicketSellerIteratorImplTest {
    private final SimpleCarriageFactory factory = new SimpleCarriageFactory();
    @Test
    void sellTicket() {
        Train train = new Train();
        train.addCarriage(CarriageType.COACH, factory);
        TicketSellerIteratorImpl seller = new TicketSellerIteratorImpl();
        Person person = anyValidPerson(25);

        boolean success = seller.sellTicket(train, 1, 25, person);

        assertThat(success, is(true));
    }
}