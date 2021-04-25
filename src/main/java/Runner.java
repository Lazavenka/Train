import domain.*;
import domain.service.*;
import driver.access.rule.AccessDecisionMaker;
import driver.access.rule.AgeDenyRule;
import driver.access.rule.DriverDenyRule;
import driver.access.rule.LicenseDenyRule;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Train train = new Train();
        TicketSeller ticketSeller = new TicketSellerIteratorImpl();
        CargoProcessor cargoProcessor = new CargoProcessorImpl();
        SimpleCarriageFactory factory = new SimpleCarriageFactory();

        List<DriverDenyRule> rules = new ArrayList<>();
        rules.add(new AgeDenyRule());
        rules.add(new LicenseDenyRule());
        AccessDecisionMaker accessDecisionMaker = new AccessDecisionMaker(rules);

        Person person = new Person(Age.of(17), "Konstanty", "Kalinowski");

        Person personDriver = new Person(Age.of(25), "Tadeusz", "Kościuszko");
        personDriver.setDriverLicense(true);

        train.appendLocomotive(factory.createCarriage(CarriageType.LOCOMOTIVE));
        train.addCarriage(factory.createCarriage(CarriageType.COACH));
        train.addCarriage(factory.createCarriage(CarriageType.COACH));
        train.addCarriage(factory.createCarriage(CarriageType.FREIGHT_CARRIAGE));
        train.addCarriage(factory.createCarriage(CarriageType.FREIGHT_CARRIAGE));


        train.setTrainDriver(personDriver, accessDecisionMaker);

        Cargo potato = new Cargo("Potato", 10_000);
        Cargo morePotato = new Cargo("Potato", 80_000);

        ticketSeller.provideAvailableSeats(train).entrySet().forEach(System.out::println);

        boolean sellTicked = ticketSeller.sellTicket(train, 4, 2, person);
        System.out.println(sellTicked);
        ticketSeller.provideAvailableSeats(train).entrySet().forEach(System.out::println);

        System.out.println("-----------------------------");

        cargoProcessor.provideAllCargo(train).forEach(System.out::println);

        final int addCargo = cargoProcessor.addCargo(train, potato);

        final int carriageWithPotatoNumber = cargoProcessor.findCargo(train, potato);

        System.out.println("Potato in carriage #" + carriageWithPotatoNumber);
        cargoProcessor.provideAllCargo(train).forEach(System.out::println);
    }
}
