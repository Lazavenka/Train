package domain.service;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargoProcessorImpl implements CargoProcessor {

    @Override
    public int addCargo(Train train, Cargo cargo) {
        for (Carriage carriage: train){
            if (carriage instanceof FreightCarriage){
                final int carriageNumber = ((FreightCarriage) carriage).addCargo(cargo);
                if (carriageNumber > 0) {
                    return carriageNumber;
                }
            }
        }
        return -1;
    }

    @Override
    public int findCargo(Train train, Cargo cargo) {
        for (Carriage carriage : train) {
            if (carriage instanceof FreightCarriage) {
                if (((FreightCarriage) carriage).containsCargo(cargo)) {
                    return carriage.getCarriageNumber();
                }
            }
        }
        return -1;
    }

    @Override
    public List<Cargo> provideAllCargo(Train train) {
        List<Cargo> allCargo = new ArrayList<>();
        for (Carriage carriage : train) {
            if (carriage instanceof FreightCarriage) {
                ((FreightCarriage) carriage).getCargoList().forEach(System.out::println);
            }
        }
        return allCargo;
    }

}
