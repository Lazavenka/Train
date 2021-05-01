import domain.*;
import factory.SimpleCarriageFactory;
import service.*;
import driver.access.rule.AccessDecisionMaker;
import driver.access.rule.AgeDenyRule;
import driver.access.rule.DriverDenyRule;
import driver.access.rule.LicenseDenyRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        Train train = new Train("FF123");
        TicketSeller ticketSeller = new TicketSellerImpl();
        CargoProcessor cargoProcessors = new CargoProcessorImpl();
        SimpleCarriageFactory factory = new SimpleCarriageFactory();

        List<DriverDenyRule> rules = new ArrayList<>();
        rules.add(new AgeDenyRule());
        rules.add(new LicenseDenyRule());
        AccessDecisionMaker accessDecisionMaker = new AccessDecisionMaker(rules);

        TrainDriverProcessor trainDriverProcessor = new TrainDriverProcessorImpl();

        Person person = new Person("Konstanty", "Kalinowski", LocalDate.of(2005, 4, 9));

        Person personDriver = new Person( "Tadeusz", "Kościuszko", LocalDate.of(1995, 8, 10));
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

        cargoProcessors.provideAllCargo(train).forEach(entry -> logger.info(entry.toString()));

        final int addPotato = cargoProcessors.addCargo(train, potato);
        final int addSugar = cargoProcessors.addCargo(train, sugar);
        final int addMorePotato = cargoProcessors.addCargo(train, morePotato);
        logger.info(addPotato + " " + addSugar + " " + addMorePotato);
        final int carriageWithPotatoNumber = cargoProcessors.findCargo(train, potato);

        logger.info("Potato in carriage #" + carriageWithPotatoNumber);
        cargoProcessors.provideAllCargo(train).forEach(entry -> logger.info(entry.toString()));

        Coach coach = new Coach(4, 25_000);
        logger.info(coach.provideEmptySeatMap().toString());
    }
}
