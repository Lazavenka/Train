package domain;

import domain.service.CargoProcessor;
import domain.service.CargoProcessorImpl;
import domain.service.TicketSeller;
import domain.service.TicketSellerImpl;

import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        Train train = new Train();
        TicketSeller ticketSeller = new TicketSellerImpl();
        CargoProcessor cargoProcessor = new CargoProcessorImpl();
        SimpleCarriageFactory factory = new SimpleCarriageFactory();
        Person person = new Person(Age.of(17), "Konstanty", "Kalinowski");
        Person personDriver = new Person(Age.of(25), "Tadeusz ", )
        train.appendLocomotive(factory.createCarriage(CarriageType.LOCOMOTIVE));
        train.addCarriage(factory.createCarriage(CarriageType.FREIGHT_CARRIAGE));
        train.addCarriage(factory.createCarriage(CarriageType.COACH));
        train.addCarriage(factory.createCarriage(CarriageType.COACH));
        train.addCarriage(factory.createCarriage(CarriageType.COACH));


        ticketSeller.provideAvailableSeats(train).
                entrySet().forEach(System.out::println);

    }
}
