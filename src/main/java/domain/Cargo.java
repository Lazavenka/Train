package domain;

import java.util.Objects;
import java.util.UUID;

public class Cargo {
    private final UUID uuid;
    private final String name;
    private final long weight;

    public Cargo(String name, long weight) {
        this.name = name;
        this.weight = weight;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public long getWeight() {
        return weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return uuid.equals(cargo.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Cargo -> "+ name + ", weight = " + weight;
    }
}
