package domain;

import data.CargoSamples;
import org.junit.jupiter.api.Test;

import static data.CargoSamples.anyCargo;
import static data.CargoSamples.anyValidCargo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        FreightCarriage carriage = new FreightCarriage(13_000, 25_000);

        Cargo potato = anyCargo("Potato", 10_000);
        final boolean success = carriage.addCargo(potato);
        final boolean secondTry = carriage.addCargo(potato);

        assertThat(success, is(true));
        assertThat(secondTry, is(false));

    }
    @Test
    void addCargo_isNotEnoughCapacity() {
        FreightCarriage carriage = new FreightCarriage(13_000, 5_000);

        Cargo potato = anyCargo("Potato", 10_000);
        final boolean success = carriage.addCargo(potato);

        assertThat(success, is(false));
    }

}