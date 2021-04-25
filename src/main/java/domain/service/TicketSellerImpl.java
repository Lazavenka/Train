package domain.service;

import domain.Carriage;
import domain.Coach;
import domain.Person;
import domain.Train;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TicketSellerImpl implements TicketSeller {
    @Override
    public boolean sellTicket(Train train, int carriageNumber, int seatNumber, Person person) {
        Carriage carriage = train.getHead();
        UUID seatID;
        while (carriage != null) {
            if (checkConditions(carriageNumber, seatNumber, carriage)) {
                ((Coach) carriage).reserveSeat(seatNumber);
                seatID = ((Coach) carriage).getSeatID(seatNumber);
                person.setTicket(seatID);
                return true;
            }
            carriage = carriage.getNext();
        }
        return true;
    }

    private boolean checkConditions(int carriageNumber, int seatNumber, Carriage carriage) {
        return carriage instanceof Coach && carriage.getCarriageNumber() == carriageNumber &&
                ((Coach) carriage).provideEmptySeatNumbersList().contains(seatNumber);
    }

    @Override
    public Map<Integer, List<Integer>> provideAvailableSeats(Train train) {
        Carriage carriage = train.getHead();
        Map<Integer, List<Integer>> availableSeats = new HashMap<>();
        while (carriage != null) {
            if (carriage instanceof Coach) {
                availableSeats.put(carriage.getCarriageNumber(),
                        ((Coach) carriage).provideEmptySeatNumbersList());
            }
            carriage = carriage.getNext();
        }
        return availableSeats;
    }
}
