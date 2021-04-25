package data;

import domain.Cargo;

public class CargoSamples {
    public static Cargo anyValidCargo(){
        return new Cargo("Potato", 10_000);
    }
}
