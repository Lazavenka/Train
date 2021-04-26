package domain;

import org.junit.jupiter.api.Test;

import static data.CargoSamples.anyCargo;
import static data.CarriageSamples.anyFreightCarriage;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FreightCarriageTest {

    @Test
    void addCargo() {
        FreightCarriage carriage = new FreightCarriage(13_000, 15_000);

        Cargo potato = anyCargo("Potato", 10_000);
        final boolean success = carriage.addCargo(potato);

        assertThat(success, is(true));

    }

    @Test
    void addCargo_doubleCargo() {
        FreightCarriage carriage = anyFreightCarriage(13_000, 25_000);

        Cargo potato = anyCargo("Potato", 10_000);
        final boolean success = carriage.addCargo(potato);
        final boolean secondTry = carriage.addCargo(potato);

        assertThat(success, is(true));
        assertThat(secondTry, is(false));

    }

    @Test
    void addCargo_isNotEnoughCapacity() {
        FreightCarriage carriage = anyFreightCarriage(13_000, 5_000);
        Cargo potato = anyCargo("Potato", 10_000);

        final boolean success = carriage.addCargo(potato);

        assertThat(success, is(false));
    }

}