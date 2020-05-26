package converter;

public class Number {
    private IntegerPart integer;
    private FractionalPart fractional;
    private int radix;
    private boolean hasFractional;

    public Number(String number, int radix) {
        this.radix = radix;
        this.hasFractional = hasFractional(number);

        if (!hasFractional) {
            this.integer = new IntegerPart(number);
            this.fractional = new FractionalPart("0");
        } else {
            String[] numberParts = number.split("\\.");
            this.integer = new IntegerPart(numberParts[0]);
            this.fractional = new FractionalPart(numberParts[1]);
        }
    }

    public Number(IntegerPart integer, FractionalPart fractional, int radix) {
        this.integer = integer;
        this.fractional = fractional;
        this.radix = radix;
        this.hasFractional = hasFractional();
    }

    public IntegerPart getInteger() {
        return integer;
    }

    public FractionalPart getFractional() {
        return fractional;
    }

    public boolean isHasFractional() {
        return hasFractional;
    }

    private boolean hasFractional(String number) {
        return number.contains(".");
    }

    private boolean hasFractional() {
        return "0".equals(fractional.getNumberString());
    }

    @Override
    public String toString() {
        String fullNum = integer.getNumberString() + "." + fractional.getNumberString();
        StringBuilder number = new StringBuilder(fullNum);
        int length = fullNum.length();

        if (fullNum.endsWith(".0")) {
            number.delete(length - 2, length);
        }

        return String.valueOf(number);
    }
}
