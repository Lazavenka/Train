package service;

import domain.Person;
import domain.Train;
import driver.access.rule.AccessDecisionMaker;
import org.junit.jupiter.api.Test;

import static data.CarriageSamples.anyLocomotive;
import static data.DenyRulesSamples.getDenyRules;
import static data.PersonSamples.*;
import static data.TrainSamples.anyCorrectPassengerTrain;
import static data.TrainSamples.anyEmptyTrain;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TrainDriverProcessorImplTest {
    final TrainDriverProcessor trainDriverProcessor = new TrainDriverProcessorImpl();
    AccessDecisionMaker accessDecisionMaker = new AccessDecisionMaker(getDenyRules());

    @Test
    void setTrainDriver() {
        Train train = anyCorrectPassengerTrain();
        Person trainDriver = anyValidDriver();

        final boolean success = trainDriverProcessor.setTrainDriver(train, trainDriver, accessDecisionMaker);

        assertThat(success, is(true));
    }

    @Test
    void setTrainDriver_incorrectDriver() {
        Train train = anyCorrectPassengerTrain();
        Person trainDriver = anyValidPerson(13);

        final boolean success = trainDriverProcessor.setTrainDriver(train, trainDriver, accessDecisionMaker);

        assertThat(success, is(false));
    }

    @Test
    void setTrainDriver_emptyTrain() {
        Train train = anyEmptyTrain();
        Person trainDriver = anyValidDriver();

        final boolean success = trainDriverProcessor.setTrainDriver(train, trainDriver, accessDecisionMaker);

        assertThat(success, is(false));
    }

    @Test
    void setTrainTwoDriver_DoubleLocomotive() {
        Train train = anyEmptyTrain();
        train.appendLocomotive(anyLocomotive());
        train.appendLocomotive(anyLocomotive());

        Person trainDriver = anyValidDriver();
        Person secondTrainDriver = anyAnotherValidDriver();

        final boolean firstSuccess = trainDriverProcessor.setTrainDriver(train, trainDriver, accessDecisionMaker);
        final boolean secondSuccess = trainDriverProcessor.setTrainDriver(train, secondTrainDriver, accessDecisionMaker);

        assertThat(firstSuccess, is(true));
        assertThat(secondSuccess, is(true));
    }
}