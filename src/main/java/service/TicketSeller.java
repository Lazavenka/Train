package service;

import domain.Person;
import domain.Train;

import java.util.List;
import java.util.Map;

public interface TicketSeller {
    boolean sellTicket(Train train, int carriageNumber, int seatNumber, Person person);

    Map<Integer, List<Integer>> provideAvailableSeats(Train train);
}
