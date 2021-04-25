package domain;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public class Coach extends Carriage {
    private final int maxNumberOfPassengers;
    private final Map<Integer, Seat> seats;

    public Coach() {
        this(40, 58_000);
    }

    public Coach(int maxNumberOfPassengers, long carriageWeight) {
        super(carriageWeight);
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        seats = new HashMap<>();
        IntStream.range(1, maxNumberOfPassengers + 1).forEach(i -> seats.put(i, new Seat(true)));
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public Map<Integer, Seat> getSeats() {
        return seats;
    }

    public UUID getSeatID(int seatNumber) {
        checkArgument(seatNumber > 0, "Seat number must be positive!",
                new IllegalArgumentException());
        checkArgument(seatNumber < this.maxNumberOfPassengers,
                "Must be less than max number of passengers", new IllegalArgumentException());
        return this.seats.get(seatNumber).getSeatId();
    }
    public UUID reserveSeat(int seatNumber){
        if(provideEmptySeatNumbersList().contains(seatNumber)){
            seats.get(seatNumber).changeStatus(false);
            return getSeatID(seatNumber);
        }
        return null;
    }
    public List<Seat> provideEmptySeatList(){
        return seats.values().stream().filter(Seat::isEmpty).collect(Collectors.toList());
    }
    public List<Integer> provideEmptySeatNumbersList(){
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Seat> entry: seats.entrySet()){
            if(entry.getValue().isEmpty()){
                list.add(entry.getKey());
            }
        }
        return list;
    }
    public Map<Integer, UUID> provideEmptySeatMap(){
        Map<Integer, UUID> map = new HashMap<>();
        for (Map.Entry<Integer, Seat> entry: seats.entrySet()){
            if (entry.getValue().isEmpty()){
                map.put(entry.getKey(),  entry.getValue().getSeatId());
            }
        }
        return map;
    }
}

class Seat{
    private final UUID seatId;
    private boolean empty;

    Seat(boolean empty){
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
