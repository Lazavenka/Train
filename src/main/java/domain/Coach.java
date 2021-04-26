package domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coach extends Carriage {
    private final int maxNumberOfPassengers;
    private final Map<Integer, Seat> seats;

    //default constructor used in factory
    public Coach() {
        this(20, 58_000);
    }

    public Coach(int maxNumberOfPassengers, long carriageWeight) {
        super(carriageWeight);
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        seats = new HashMap<>();
        IntStream.range(1, maxNumberOfPassengers + 1).forEach(i -> seats.put(i, new Seat(true)));
    }

    public UUID getSeatID(int seatNumber) {
        return seatNumber > 0 && seatNumber <= this.maxNumberOfPassengers ? this.seats.get(seatNumber).getSeatId() : null;
    }

    public Optional<UUID> reserveSeat(int seatNumber) {
        if (provideEmptySeatNumbersList().contains(seatNumber)) {
            seats.get(seatNumber).changeStatus(false);
            return Optional.ofNullable(getSeatID(seatNumber));
        }
        return Optional.empty();
    }

    public List<Seat> provideEmptySeatList() {
        return seats.values().stream().filter(Seat::isEmpty).collect(Collectors.toList());
    }

    //Тренировка по Stream.api
    public List<Integer> provideEmptySeatNumbersList() {
        List<Integer> list;
        list = seats.entrySet()
                .stream().filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return list;
    }
    //Тренировка по Stream.api
    public Map<Integer, UUID> provideEmptySeatMap() {
        Map<Integer, UUID> map;
        map = seats.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getSeatId()));

        return map;
    }
}

class Seat {
    private final UUID seatId;
    private boolean empty;

    Seat(boolean empty) {
        this.seatId = UUID.randomUUID();
        this.empty = empty;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public boolean isEmpty() {
        return empty;
    }

    protected void changeStatus(boolean empty) {
        this.empty = empty;
    }
}
