package domain.service;

import domain.Cargo;
import domain.Train;

import java.util.List;

public interface CargoProcessor {
    int addCargo(Train train, Cargo cargo);
    int findCargo(Train train, Cargo cargo);
    List<Cargo> provideAllCargo(Train train);

}
