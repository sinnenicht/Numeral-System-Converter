package converter;

public class FractionalPart {
    private StringBuilder number;

    public FractionalPart(String number) {
        this.number = new StringBuilder(number);
    }

    public String getNumberString() {
        return String.valueOf(number);
    }
}
