package data;

import domain.Coach;
import domain.FreightCarriage;
import domain.Locomotive;

public class CarriageSamples {
    public static Locomotive anyLocomotive() {
        return new Locomotive(150_000, 10_000_000);
    }

    public static Locomotive anyLocomotive(long weight) {
        return anyLocomotive(weight, 10_000_000);
    }

    public static Locomotive anyLocomotive(long weight, long thrust) {
        return new Locomotive(weight, thrust);
    }

    public static FreightCarriage anyFreightCarriage() {
        return new FreightCarriage(23_000, 65_000);
    }

    public static FreightCarriage anyFreightCarriage(long carriageWeight) {
        return anyFreightCarriage(carriageWeight, 65_000);
    }

    public static FreightCarriage anyFreightCarriage(long carriageWeight, long liftingCapacity) {
        return new FreightCarriage(carriageWeight, liftingCapacity);
    }

    public static Coach anyCoach() {
        return anyCoach(5);
    }

    public static Coach anyCoach(int numberOfSeats) {
        return anyCoach(numberOfSeats, 58_000);
    }

    public static Coach anyCoach(int numberOfSeats, long carriageWeight) {
        return new Coach(numberOfSeats, carriageWeight);
    }
}
