package NumeralSystemConverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the radix.");
            int sourceRadix = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the number.");
            String sourceNumber = sc.nextLine();
            System.out.println("Enter the target radix.");
            int targetRadix = sc.nextInt();
            if (sourceRadix < 1 || targetRadix < 1 || sourceRadix > 36 ||  targetRadix > 36) {
                System.out.println("error");
                return;
            } else if(sourceRadix == 1 && !sourceNumber.matches("^(1)\\1+$")) {
                System.out.println("error");
                return;
            }
            convertFractionToDecimal(sourceRadix, sourceNumber, targetRadix);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public static void convertFractionToDecimal(int sourceRadix, String sourceNumber, int targetRadix) {
        if(!sourceNumber.contains(".")) {
            System.out.println(convertNumberToAndFromAnyRadix(sourceRadix, sourceNumber, targetRadix));
            return;
        }

        if (sourceRadix == 1) {
            System.out.println(convertNumberToAndFromAnyRadix(1, sourceNumber, targetRadix));
        } else if (sourceRadix != 10) {
            String[] fractionParts = sourceNumber.split("\\.");
            String integer = fractionParts[0];
            String[] fractional = fractionParts[1].split("");
            integer = convertNumberToAndFromAnyRadix(sourceRadix, integer, 10);
            double temp = 0;

            for (int i = 0; i < fractional.length;) {
                temp += Character.getNumericValue(fractional[i].charAt(0)) / Math.pow(sourceRadix, ++i);
            }

            temp += Double.parseDouble(integer);
            integer = String.valueOf(temp);
            convertFractionToDecimal(10, integer, targetRadix);
        } else {
            String[] fractionParts = sourceNumber.split("\\.");
            String integer = fractionParts[0];
            String fraction = fractionParts[1];
            String tempFraction = "0." + fraction;
            String newFraction = "";

            if (targetRadix == 1) {
                System.out.println(convertNumberToAndFromAnyRadix(10, integer, 1));
                return;
            }

            integer = convertNumberToAndFromAnyRadix(sourceRadix, integer, targetRadix);

            for (int i = 0; i < 5; i++) {
                tempFraction = String.valueOf(Double.parseDouble(tempFraction) * targetRadix);
                String[] parts = tempFraction.split("\\.");
                newFraction = newFraction.concat(String.valueOf(Character.forDigit(Integer.parseInt(parts[0]), targetRadix)));
                tempFraction = "0." + parts[1];
            }

            String answer = integer + "." + newFraction;
            System.out.printf(answer);
        }

    }

    public static String convertNumberToAndFromAnyRadix(int sourceRadix, String sourceNumber, int targetRadix) {
        if (sourceRadix == 1) {
            sourceNumber = String.valueOf(sourceNumber.length());
        } else if (sourceRadix != 10) {
            sourceNumber = String.valueOf(Integer.parseInt(sourceNumber, sourceRadix));
        }

        if (targetRadix == 1) {
            String s = "";
            for (int i = 0; i < Integer.parseInt(sourceNumber); i++) {
                s = s.concat("1");
            }
            return s;
        } else {
            return Integer.toString(Integer.parseInt(sourceNumber), targetRadix);
        }
    }
}