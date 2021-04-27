package service;

import domain.Carriage;
import domain.Locomotive;
import domain.Person;
import domain.Train;
import driver.access.rule.AccessDecisionMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainDriverProcessorImpl implements TrainDriverProcessor {
    private final Logger logger = LoggerFactory.getLogger(TrainDriverProcessorImpl.class);

    @Override
    public boolean setTrainDriver(Train train, Person person, AccessDecisionMaker accessDecisionMaker) {
        if (!accessDecisionMaker.hasAccess(person)) {
            logger.info("Person has not access to drive. Return false.");
            return false;
        }
        for (Carriage carriage : train) {
            if (carriage instanceof Locomotive && isDriverEmpty((Locomotive) carriage)) {
                ((Locomotive) carriage).setDriver(person);
                logger.info("Person " + person + " set in carriage #" + carriage.getCarriageNumber() + " as driver.");
                return true;
            }
        }
        return false;
    }

    private boolean isDriverEmpty(Locomotive locomotive) {
        return locomotive.getDriver() == null;
    }

    @Override
    public boolean removeRemoveDriver(Train train) {
        throw new UnsupportedOperationException("Not implement yet");
    }
}
