package domain;

import java.util.ArrayList;
import java.util.List;

public class FreightCarriage extends Carriage {
    private final long liftingCapacity;
    private final List<Cargo> cargoList;

    //default constructor used in factory
    public FreightCarriage() {
        this(23_000, 68_000);
    }

    public FreightCarriage(long carriageWeight, long liftingCapacity) {
        super(carriageWeight);
        this.liftingCapacity = liftingCapacity;
        cargoList = new ArrayList<>();
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public boolean addCargo(Cargo cargo) {
        if (isAvailableForLoadCargo(cargo) && !cargoList.contains(cargo)) {
            this.cargoList.add(cargo);
            return true;
        } else {
            return false;
        }

    }

    private boolean isAvailableForLoadCargo(Cargo cargo) {
        return (this.liftingCapacity - getTotalCargoWeight()) >= cargo.getWeight();
    }

    private long getTotalCargoWeight() {
        return cargoList.stream().mapToLong(Cargo::getWeight).sum();
    }

    @Override
    public long getTotalWeight() {
        return weight + getTotalCargoWeight();
    }
}
