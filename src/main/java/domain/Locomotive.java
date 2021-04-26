package domain;

public class Locomotive extends Carriage {
    private Person driver;
    private final long thrust;

    public Locomotive() {
        this(180_000, 5_000_000);
    }

    public Locomotive(long weight, long thrust) {
        super(weight);
        this.thrust = thrust;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

}
