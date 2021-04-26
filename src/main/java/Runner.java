import domain.*;
import domain.service.*;
import driver.access.rule.AccessDecisionMaker;
import driver.access.rule.AgeDenyRule;
import driver.access.rule.DriverDenyRule;
import driver.access.rule.LicenseDenyRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        Train train = new Train("FF123");
        TicketSeller ticketSeller = new TicketSellerImpl();
        CargoProcessor cargoProcessor = new CargoProcessorImpl();
        SimpleCarriageFactory factory = new SimpleCarriageFactory();

        List<DriverDenyRule> rules = new ArrayList<>();
        rules.add(new AgeDenyRule());
        rules.add(new LicenseDenyRule());
        AccessDecisionMaker accessDecisionMaker = new AccessDecisionMaker(rules);

        TrainDriverProcessor trainDriverProcessor = new TrainDriverProcessorImpl();

        Person person = new Person(Age.of(17), "Konstanty", "Kalinowski");

        Person personDriver = new Person(Age.of(25), "Tadeusz", "Kościuszko");
        personDriver.setDriverLicense(true);

        train.appendLocomotive((Locomotive) factory.createCarriage(CarriageType.LOCOMOTIVE));
        train.addCarriage(factory.createCarriage(CarriageType.COACH));
        train.addCarriage(factory.createCarriage(CarriageType.COACH));
        train.addCarriage(factory.createCarriage(CarriageType.FREIGHT_CARRIAGE));
        train.addCarriage(factory.createCarriage(CarriageType.FREIGHT_CARRIAGE));

        final boolean setTrainDriver = trainDriverProcessor.setTrainDriver(train, personDriver, accessDecisionMaker);
        logger.info("Set driver - " + setTrainDriver);

        Cargo potato = new Cargo("Potato", 10_000);
        Cargo sugar = new Cargo("Sugar", 5_000);
        Cargo morePotato = new Cargo("Potato", 80_000);

        ticketSeller.provideAvailableSeats(train).entrySet().forEach(entry -> logger.info(entry.toString()));

        boolean sellTicked = ticketSeller.sellTicket(train, 3, 2, person);
        logger.info("Sell ticket - " + sellTicked);
        ticketSeller.provideAvailableSeats(train).entrySet().forEach(entry -> logger.info(entry.toString()));

        System.out.println("-----------------------------");

        cargoProcessor.provideAllCargo(train).forEach(entry -> logger.info(entry.toString()));

        final int addPotato = cargoProcessor.addCargo(train, potato);
        final int addSugar = cargoProcessor.addCargo(train, sugar);
        final int addMorePotato = cargoProcessor.addCargo(train, morePotato);
        logger.info(addPotato + " " + addSugar + " " + addMorePotato);
        final int carriageWithPotatoNumber = cargoProcessor.findCargo(train, potato);

        logger.info("Potato in carriage #" + carriageWithPotatoNumber);
        cargoProcessor.provideAllCargo(train).forEach(entry -> logger.info(entry.toString()));

        Coach coach = new Coach(4, 25_000);
        logger.info(coach.provideEmptySeatMap().toString());
    }
}
