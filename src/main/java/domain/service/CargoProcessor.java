package domain.service;

import domain.Cargo;
import domain.Train;

import java.util.List;
import java.util.Set;

public interface CargoProcessor {
    int addCargo(Train train, Cargo cargo);

    int findCargo(Train train, Cargo cargo);

    List<Cargo> provideAllCargo(Train train);


}
