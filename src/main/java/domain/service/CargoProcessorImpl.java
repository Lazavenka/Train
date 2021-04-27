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
                    final int carriageNumber = carriage.getCarriageNumber();
                    logger.info("Cargo " + cargo + " successfully add in carriage #" + carriageNumber);
                    return carriageNumber;
                }
            }
        }
        logger.info("Carriage is not added in train " + train.getTrainNumber());
        return -1;
    }

    @Override
    public int findCargo(Train train, Cargo cargo) {
        for (Carriage carriage : train) {
            if (carriage instanceof FreightCarriage && ((FreightCarriage) carriage).getCargoList().contains(cargo)) {
                logger.info("Find " + cargo + " in carriage #" + carriage.getCarriageNumber());
                return carriage.getCarriageNumber();
            }
        }
        logger.info("Cargo is not found in train " + train.getTrainNumber());
        return -1;
    }

    @Override
    public List<Cargo> provideAllCargo(Train train) {
        List<Cargo> allCargo = new ArrayList<>();
        for (Carriage carriage : train) {
            if (carriage instanceof FreightCarriage) {
                final List<Cargo> cargoList = ((FreightCarriage) carriage).getCargoList();
                logger.info("Carriage " + carriage.getCarriageNumber() + ", contains " +
                        cargoList.size() + " items.");
                allCargo.addAll(cargoList);
            }
        }
        return allCargo;
    }


}
