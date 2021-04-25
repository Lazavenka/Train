package domain;

public abstract class Carriage {

    protected int carriageNumber;
    protected final long weight;

    private Carriage next;

    public Carriage(long weight) {
        this.weight = weight;
    }

    public long getTotalWeight() {
        return this.weight;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public Carriage getNext() {
        return next;
    }

    public void setNext(Carriage next) {
        this.next = next;
    }
}
