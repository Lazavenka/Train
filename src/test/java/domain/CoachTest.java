package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static data.CarriageSamples.anyCoach;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class CoachTest {

    @Test
    void provideEmptySeatListIDs() {
        Coach coach = anyCoach();
        List<Seat> emptySeatList = coach.provideEmptySeatList();
        emptySeatList.forEach(seat -> assertThat(seat.isEmpty(), is(true)));
    }

    @Test
    void getSeatID_CorrectSeatNumber() {
        Coach coach = anyCoach(10);
        final int seatNumber = 5;

        final UUID seatID = coach.getSeatID(seatNumber);

        assertThat(seatID, is(notNullValue()));
    }

    @Test
    void getSeatID_incorrectSeatNumber() {
        Coach coach = anyCoach(10);
        final int seatNumber = 25;

        final UUID id = coach.getSeatID(seatNumber);

        assertThat(id, is(nullValue()));
    }

    @Test
    void provideEmptySeatList_AllSeatsReserved() {
        Coach coach = anyCoach();
        reserveAllSeats(coach);

        List<Seat> emptySeatList = coach.provideEmptySeatList();

        assertThat(emptySeatList.isEmpty(), is(true));
    }

    @Test
    void reserveSeat() {
        Coach coach = anyCoach(5);
        final int reservedSeatNumber = 2;
        final UUID generatedSeatID = coach.getSeatID(reservedSeatNumber);
        final Optional<UUID> returnedID = coach.reserveSeat(reservedSeatNumber);

        assertThat(returnedID, is(equalTo(Optional.of(generatedSeatID))));

    }

    @Test
    void reserveNonExistSeat() {
        Coach coach = anyCoach(5);
        final int reservedSeatNumber = 10;

        final Optional<UUID> returnedID = coach.reserveSeat(reservedSeatNumber);

        assertThat(returnedID, is(Optional.empty()));

    }

    private void reserveAllSeats(Coach coach) {
        List<Integer> seats = coach.provideEmptySeatNumbersList();
        for (int i : seats) {
            coach.reserveSeat(i);
        }
    }
}