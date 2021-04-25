package domain;

import driver.access.rule.AccessDecisionMaker;

import java.util.Iterator;
import java.util.ListIterator;

public class Train implements Iterable<Carriage>{

    private Carriage head;
    private Carriage tail;

    private int numberOfCarriages;

    public Carriage getHead() {
        return head;
    }

    public Carriage getTail() {
        return tail;
    }

    public int getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void addCarriage(CarriageType carriageType, SimpleCarriageFactory factory) {
        Carriage carriage = factory.createCarriage(carriageType);
        addCarriage(carriage);
    }

    //in case we want add specific carriage instance
    public void addCarriage(Carriage carriage) {
        if (this.head == null) {
            this.head = carriage;
        } else {
            this.tail.setNext(carriage);
        }
        this.tail = carriage;
        this.numberOfCarriages++;
        carriage.setCarriageNumber(numberOfCarriages);
    }

    //Add locomotive instance only in head of train
    public void appendLocomotive(Carriage locomotive) {
        locomotive.setNext(this.head);
        this.head = locomotive;
        if (this.numberOfCarriages == 0) {
            this.tail = this.head;
        }
        this.numberOfCarriages++;
    }

    public boolean setTrainDriver(Person person, AccessDecisionMaker accessDecisionMaker) {
        if (this.head instanceof Locomotive) {
            if (accessDecisionMaker.hasAccess(person)) {
                ((Locomotive) this.head).setDriver(person);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    @Override
    public Iterator<Carriage> iterator() {
        return new ListIterator<>() {
            Carriage current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Carriage next() {
                Carriage toReturn = current;
                current = current.getNext();
                return toReturn;
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Carriage previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                return current.carriageNumber;
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(Carriage carriage) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(Carriage carriage) {
                throw new UnsupportedOperationException();
            }
        };
    }
}
