package data;

import domain.Carriage;
import domain.Coach;
import domain.FreightCarriage;
import domain.Locomotive;

public class CarriageSamples {
    public static Locomotive anyLocomotive() {
        return new Locomotive(10_000_000, 150_000);
    }

    public static FreightCarriage anyFreightCarriage() {
        return new FreightCarriage(23_000, 65_000);
    }

    public static Coach anyCoach(){
        return new Coach(5, 58_000);
    }
    public static Coach anyCoach(int numberOfSeats){
        return new Coach(numberOfSeats, 58_000);
    }
}
