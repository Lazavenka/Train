package data;

import domain.Cargo;


public class CargoSamples {
    public static Cargo anyValidCargo(){
        return anyCargo("Potato", 10_000);
    }
    public static Cargo anyCargo(String name, long weight){
        return new Cargo(name, weight);
    }
}
