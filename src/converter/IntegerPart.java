package converter;

public class IntegerPart {
    private StringBuilder number;

    public IntegerPart(String number) {
        this.number = new StringBuilder(number);
    }

    public String getNumberString() {
        return String.valueOf(number);
    }
}
