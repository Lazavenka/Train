package data;

import domain.Locomotive;
import domain.Train;

import static data.CarriageSamples.*;

public class TrainSamples {
    public static Train anyCorrectPassengerTrain(){
        Train train = new Train("998M");
        train.appendLocomotive(anyLocomotive());
        train.addCarriage(anyCoach(5));
        train.addCarriage(anyCoach(5));
        return train;
    }

    public static Train anyCorrectFreightTrain(){
        Train train = new Train("998M");
        train.appendLocomotive(anyLocomotive());
        train.addCarriage(anyFreightCarriage(15_000, 60_000));
        train.addCarriage(anyFreightCarriage(15_000, 60_000));
        return train;
    }

    public static Train anyEmptyTrain(){
        return new Train("998M");
    }
}
