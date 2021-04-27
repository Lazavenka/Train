package factory;

import domain.*;

public class SimpleCarriageFactory {
    public Carriage createCarriage(CarriageType type){
        Carriage carriage = null;

        switch (type){
            case COACH -> carriage = new Coach();
            case LOCOMOTIVE -> carriage = new Locomotive();
            case FREIGHT_CARRIAGE -> carriage = new FreightCarriage();
        }
        return carriage;
    }
}
