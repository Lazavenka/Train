package domain.service;

import domain.Carriage;
import domain.Coach;
import domain.Person;
import domain.Train;

import java.util.*;

public class TicketSellerImpl implements TicketSeller {
    @Override
    public boolean sellTicket(Train train, int carriageNumber, int seatNumber, Person person) {
        UUID seatID;
        for (Carriage carriage : train) {
            if (carriage instanceof Coach && carriage.getCarriageNumber() == carriageNumber &&
                    ((Coach) carriage).provideEmptySeatNumbersList().contains(seatNumber)) {
                final Optional<UUID> reserveSeatID = ((Coach) carriage).reserveSeat(seatNumber);
                if (reserveSeatID.isPresent()) {
                    seatID = reserveSeatID.get();
                    person.setTicket(seatID);
                    return true;
                }
            }
        }
        return false;
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
