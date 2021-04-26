package domain.service;

import domain.Person;
import domain.Train;
import driver.access.rule.AccessDecisionMaker;

public interface TrainDriverProcessor {
    boolean setTrainDriver(Train train, Person person, AccessDecisionMaker accessDecisionMaker);
    boolean removeRemoveDriver(Train train);
}
