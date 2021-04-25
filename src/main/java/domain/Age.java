package domain;

import static com.google.common.base.Preconditions.checkArgument;

public class Age {
    private final int value;

    private Age(int value) {
        checkArgument(value >= 0, "Age must be positive!");
        checkArgument(value < 125, "Age must be positive!");
        this.value = value;
    }

    public static Age of(int value) {
        return new Age(value);
    }

    public int intValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
