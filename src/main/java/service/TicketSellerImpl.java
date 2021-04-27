package service;

import domain.Carriage;
import domain.Coach;
import domain.Person;
import domain.Train;

import java.util.*;

public class TicketSellerImpl implements TicketSeller {

    @Override
    public boolean sellTicket(Train train, int carriageNumber, int seatNumber, Person person) {
        for (Carriage carriage : train) {
            if (carriage instanceof Coach && carriage.getCarriageNumber() == carriageNumber) {
                final Optional<UUID> optReserveSeatID = ((Coach) carriage).reserveSeat(seatNumber);
                optReserveSeatID.ifPresentOrElse(person::setTicket, () -> person.setTicket(null));
            }
        }
        return person.getTicket() != null;
    }

    @Override
    public Map<Integer, List<Integer>> provideAvailableSeats(Train train) {
        Map<Integer, List<Integer>> availableSeats = new HashMap<>();
        for (Carriage carriage : train) {
            if (carriage instanceof Coach) {
                availableSeats.put(carriage.getCarriageNumber(),
                        ((Coach) carriage).provideEmptySeatNumbersList());
            }
        }
        return availableSeats;
    }
}
