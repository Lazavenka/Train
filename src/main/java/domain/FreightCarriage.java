package domain;

import java.util.ArrayList;
import java.util.List;

public class FreightCarriage extends Carriage {
    private final long liftingCapacity;
    private final List<Cargo> cargoList;

    public FreightCarriage() {
        this(23_000, 68_000);
    }

    public FreightCarriage(long carriageWeight, long liftingCapacity) {
        super(carriageWeight);
        this.liftingCapacity = liftingCapacity;
        cargoList = new ArrayList<>();
    }

    private long getTotalCargoWeight() {
        return cargoList.stream().mapToLong(Cargo::getWeight).sum();
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public boolean addCargo(Cargo cargo) {
        if (isAvailableForLoadCargo(cargo) && !containsCargo(cargo)) {
            this.cargoList.add(cargo);
            return true;
        } else {
            return false;
        }

    }

    private boolean isAvailableForLoadCargo(Cargo cargo) {
        final long cargoWeight = cargo.getWeight();
        final long totalCargoWeight = getTotalCargoWeight();

        return (this.liftingCapacity - totalCargoWeight) >= cargoWeight;
    }

    public boolean containsCargo(Cargo cargo) {
        return cargoList.contains(cargo);
    }

    @Override
    public long getTotalWeight() {
        return weight + getTotalCargoWeight();
    }
}
