package converter;

import java.util.Map;

public class Converter {
    private Number numToConvert;
    private int sourceRadix;
    private int targetRadix;
    private Number resultNum;

    public Converter(Number numToConvert, int sourceRadix, int targetRadix) {
        this.numToConvert = numToConvert;
        this.sourceRadix = sourceRadix;
        this.targetRadix = targetRadix;
        this.resultNum = findResult();
    }

    public Number getResultNum() {
        return resultNum;
    }

    private Number findResult() {
        IntegerPart wholePart = convertWholeNum(numToConvert.getInteger(), sourceRadix, targetRadix);
        if (!numToConvert.isHasFractional()) {
            FractionalPart noFraction = new FractionalPart("0");
            return new Number(wholePart, noFraction, targetRadix);
        } else {
            FractionalPart fractionalPart = convertFractionalNum(numToConvert.getFractional(), sourceRadix, targetRadix);
            return new Number(wholePart, fractionalPart, targetRadix);
        }
    }

    private IntegerPart convertWholeNum(IntegerPart integer, int sourceRadix, int targetRadix) {
        String newNum;

        if (sourceRadix != 10) {
            int decimalNum = convertWholeNumToDecimal(integer, sourceRadix);
            newNum = convertWholeDecimalToRadix(decimalNum, targetRadix);
        } else if (targetRadix != 10) {
            newNum = convertWholeDecimalToRadix(Integer.parseInt(integer.getNumberString()), targetRadix);
        } else {
            return integer;
        }

        return new IntegerPart(newNum);
    }

    private int convertWholeNumToDecimal(IntegerPart integer, int sourceRadix) {
        if (sourceRadix == 1) {
            return convertBase1To10(Integer.parseInt(integer.getNumberString()));
        } else {
            return Integer.parseInt(integer.getNumberString(), sourceRadix);
        }
    }

    private String convertWholeDecimalToRadix(int decimalNum, int targetRadix) {
        if (targetRadix == 1) {
            return convertBase10To1(decimalNum);
        } else {
            return Integer.toString(decimalNum, targetRadix);
        }
    }

    private static int convertBase1To10(int number) {
        return Integer.toString(number).length();
    }

    private static String convertBase10To1(int number) {
        return "1".repeat(Math.max(0, number));
    }

    private FractionalPart convertFractionalNum(FractionalPart fractional, int sourceRadix, int targetRadix) {
        Maps maps = new Maps();
        String newNum;

        if (sourceRadix != 10) {
            long decimalNum = convertFractionalNumToDecimal(fractional, sourceRadix, maps);
            newNum = convertFractionalDecimalToRadix(decimalNum, targetRadix, maps);
        } else if (targetRadix != 10) {
            newNum = convertFractionalDecimalToRadix(Integer.parseInt(fractional.getNumberString()), targetRadix, maps);
        } else {
            return fractional;
        }

        return new FractionalPart(newNum);
    }

    private long convertFractionalNumToDecimal(FractionalPart fractionalPart, int sourceRadix, Maps maps) {
        Map<Character, Integer> charIntMap = maps.getCharIntMap();

        String fractional = fractionalPart.getNumberString();
        int numDigits = fractional.length();
        double base = sourceRadix;
        double total = 0;

        for (int index = 0; index < numDigits; index++) {
            double numerator = charIntMap.get(fractional.charAt(index));
            total += numerator / base;
            base *= sourceRadix;
        }

        StringBuilder decimal = new StringBuilder();
        decimal.append(total);
        decimal.delete(0, 2);
        String decimalFraction = decimal.toString();

        return Long.parseLong(decimalFraction);
    }

    private String convertFractionalDecimalToRadix(long decimalNum, int targetRadix, Maps maps) {
        Map<Integer, Character> intCharMap = maps.getIntCharMap();

        // turn decimalNum into a double
        StringBuilder decimal = new StringBuilder();
        decimal.append(decimalNum);
        decimal.insert(0, "0.");
        double decimalFraction = Double.parseDouble(String.valueOf(decimal));

        // convert double to desired radix
        StringBuilder fractional = new StringBuilder();

        for (int digits = 0; digits < 5; digits++) {
            decimalFraction *= targetRadix;
            String[] splitNum = String.valueOf(decimalFraction).split("\\.");
            fractional.append(intCharMap.get(Integer.parseInt(splitNum[0])));

            String newNumToMultiply = "0." + splitNum[1];
            decimalFraction = Double.parseDouble(newNumToMultiply);
        }

        return String.valueOf(fractional);
    }
}
