package enums;

public enum DigitsAfterDecimalPoint {
    TWO ("2"),
    THREE ("3"),
    FOUR ("4");

    String number;

    DigitsAfterDecimalPoint(String number) {
        this.number = number;
    }

    public String getValue() {
        return number;
    }

    public static DigitsAfterDecimalPoint getByValue(String value) {
        for (DigitsAfterDecimalPoint digits : DigitsAfterDecimalPoint.values()) {
            if (value.equals(digits.getValue()))
                return digits;
        }
        throw new IllegalArgumentException("No DigitsAfterDecimalPoint with value " + value);
    }
}
