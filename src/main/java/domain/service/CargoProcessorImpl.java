package domain.service;

import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CargoProcessorImpl implements CargoProcessor {
    private final Logger logger = LoggerFactory.getLogger(CargoProcessorImpl.class);

    @Override
    public int addCargo(Train train, Cargo cargo) {
        for (Carriage carriage : train) {
            if (carriage instanceof FreightCarriage) {
                final boolean success = ((FreightCarriage) carriage).addCargo(cargo);
                if (success) {
                    return carriage.getCarriageNumber();
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
                logger.debug("Carriage " + carriage.getCarriageNumber() + ", contains " +
                        ((FreightCarriage) carriage).getCargoList().size() + " items.");
                allCargo.addAll(((FreightCarriage) carriage).getCargoList());
            }
        }
        return allCargo;
    }


}
