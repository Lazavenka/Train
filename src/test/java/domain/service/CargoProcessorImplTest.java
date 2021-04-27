package domain.service;

import domain.Cargo;
import domain.Train;
import org.junit.jupiter.api.Test;

import static data.CargoSamples.anyCargo;
import static data.CargoSamples.anyValidCargo;
import static data.TrainSamples.anyCorrectFreightTrain;
import static data.TrainSamples.anyCorrectPassengerTrain;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class CargoProcessorImplTest {
    private final CargoProcessor cargoProcessor = new CargoProcessorImpl();

    @Test
    void addCargo() {
        Train train = anyCorrectFreightTrain(); //Freight carriage default lifting limit - 60_000

        Cargo potato = anyValidCargo(); //default potato,  weight -> 10_000

        final int carriageNumber = cargoProcessor.addCargo(train, potato);

        assertThat(carriageNumber, is(equalTo(2)));
    }

    @Test
    void addCargo_inAnotherCarriage() {
        Train train = anyCorrectFreightTrain();
        Cargo potato = anyValidCargo(); //default potato,  weight -> 10_000
        Cargo heavierPotato = anyCargo("Potato", 55_000);
        putCargo(train, potato);

        assertThat(cargoProcessor.findCargo(train, potato), is(equalTo(2)));

        final int carriageNumber = cargoProcessor.addCargo(train, heavierPotato);

        assertThat(carriageNumber, is(equalTo(3)));
    }

    @Test
    void addToHeavyCargo() {
        Train train = anyCorrectFreightTrain();

        Cargo potato = anyCargo("Potato", 10_000_000); //default potato,  weight -> 10_000

        final int carriageNumber = cargoProcessor.addCargo(train, potato);

        assertThat(carriageNumber, is(equalTo(-1)));
    }

    @Test
    void addCargo_incorrectTrain() {
        Train train = anyCorrectPassengerTrain();

        Cargo potato = anyCargo("Potato", 10_000); //default potato,  weight -> 10_000

        final int carriageNumber = cargoProcessor.addCargo(train, potato);

        assertThat(carriageNumber, is(equalTo(-1)));
    }

    @Test
    void findCargo() {
        Train train = anyCorrectFreightTrain();
        Cargo potato = anyValidCargo(); //default potato,  weight -> 10_000
        cargoProcessor.addCargo(train, potato);

        final int carriageNumberWithCargo = cargoProcessor.findCargo(train, potato);

        assertThat(carriageNumberWithCargo, is(not(-1)));
    }

    @Test
    void findCargo_another() {
        Train train = anyCorrectFreightTrain();
        Cargo potato = anyValidCargo(); //default potato,  weight -> 10_000
        Cargo anotherPotato = anyValidCargo();
        putCargo(train, potato);

        final int carriageNumberWithCargo = cargoProcessor.findCargo(train, anotherPotato);

        assertThat(carriageNumberWithCargo, is(-1));
    }

    private void putCargo(Train train, Cargo cargo) {
        cargoProcessor.addCargo(train, cargo);
    }
}