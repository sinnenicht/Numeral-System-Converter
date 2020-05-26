package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isWorking = true;
        Scanner scanner = new Scanner(System.in);

        while (isWorking) {
            String srcRadix = scanner.nextLine();
            if (isInvalidNumber(srcRadix)) {
                System.out.println("Error: not a number.");
                break;
            }

            int sourceRadix = Integer.parseInt(srcRadix);
            if (isInvalidRadix(sourceRadix)) {
                System.out.println("Error: number is not a valid radix.");
                break;
            }

            String sourceNum = scanner.nextLine();
            if (isInvalidNumForRadix(sourceNum, sourceRadix)) {
                System.out.println("Error: impossible number for given radix.");
                break;
            }

            String trgtRadix = scanner.nextLine();
            if (isInvalidNumber(trgtRadix)) {
                System.out.println("Error: not a number.");
                break;
            }

            int targetRadix = Integer.parseInt(trgtRadix);
            if (isInvalidRadix(targetRadix)) {
                System.out.println("Error: number is not a valid radix.");
                break;
            }

            Number numToConvert = new Number(sourceNum, sourceRadix);
            Converter converter = new Converter(numToConvert, sourceRadix, targetRadix);
            Number result = converter.getResultNum();

            System.out.println(result.toString());
            isWorking = false;
        }
    }

    public static boolean isInvalidNumber(String number) {
        for (int index = 0; index < number.length(); index++) {
            if (number.charAt(index) > '9' || number.charAt(index) < '0') {
                return true;
            }
        }

        return false;
    }

    public static boolean isInvalidRadix(int radix) {
        return radix > 36 || radix < 1;
    }

    public static boolean isInvalidNumForRadix(String number, int radix) {
        char largestPossibleChar = findLargestCharInRadix(radix);
        int numDigits = number.length();

        for (int index = 0; index < numDigits; index++) {
            if (number.charAt(index) > largestPossibleChar) {
                return true;
            }
        }

        return false;
    }

    public static char findLargestCharInRadix(int radix) {
        if (radix > 10) {
            return (char) ('a' + (radix - 11));
        } else {
            return (char) ('0' + radix);
        }
    }
}
