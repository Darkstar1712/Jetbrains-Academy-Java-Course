package BullsAndCows;
import java.util.Scanner;
import java.util.Random;

public class BullsAndCows {
    public static void main(String[] args) {
        start(getCode());
    }

    public static void start(String code) {
        Scanner scanner = new Scanner(System.in);
        int turnNumber = 1;

        if (code == null) {
            return;
        }

        if (code.equals("")) {
            return;
        }

        System.out.println("Okay, let's start a game!");

        while (true) {
            System.out.println("Turn " + turnNumber + ":");
            String guess = scanner.nextLine();

            if ("".equals(guess)) {
                System.out.println("Error: guess cannot be empty");
                return;
            }

            grader(code, guess);
            turnNumber++;

            if(guess.equals(code)) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }

    public static String getCode() {
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        int range = 0;

        System.out.println("Input the length of the secret code:");

        try {
            length = scanner.nextInt();
        } catch (Exception e)  {
            System.out.println("Error: Not a valid number.");
            return null;
        }

        if (length == 0) {
            System.out.println("Error: input must be greater than 0");
            return null;
        } else if (length > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return null;
        }

        System.out.println("Input the number of possible symbols in the code:");

        try {
            range = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Not a valid number.");
            return null;
        }

        if (range > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return null;
        } else if (range == 0) {
            System.out.println("Error: input must be greater than 0");
            return null;
        } else if (range < length) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + range + " unique symbols.");
            return null;
        }

        return pseudoRandomNumberGenerator(length, range);
    }

    public static String pseudoRandomNumberGenerator(int length, int range) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
        String result = "";
        String numberRange;
        String letterRange;



        while (result.length() != length) {
            int randomNumber = random.nextInt(range);

            if (!result.contains(String.valueOf(chars.charAt(randomNumber)))) {
                result += String.valueOf(chars.charAt(randomNumber));
            }
        }
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < length; i ++) {
            System.out.print("*");
        }
        if(range == 1) {
            System.out.print(" (0).");
        } else if (range <= 10) {
            System.out.println(" (0-" + chars.charAt(range - 1) + ").");
        } else if (range == 11) {
            System.out.println(" (0-9" + ", a).");
        } else if (range <= 36) {
            System.out.println(" (0-9" + ", a-" + chars.charAt(range - 1) + ").");
        }
        return result;
    }

    public static void grader(String code, String guess) {
        int bulls = 0;
        int cows = 0;
        String cowName = "cow";
        String bullName = "bull";

        for (int i = 0; i < guess.length(); i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (code.indexOf(guess.charAt(i)) >= 0) {
                cows++;
            }
        }

        if (bulls != 1) {
            bullName = "bulls";
        }

        if (cows != 1) {
            cowName = "cows";
        }

        if (bulls > 0 && cows > 0) {
            System.out.println("Grade: " + bulls + " " + bullName + " and " + cows + " " + cowName + ".");
        } else if (bulls > 0 && cows == 0) {
            System.out.println("Grade: " + bulls + " " + bullName + ".");
        } else if (bulls == 0 && cows > 0) {
            System.out.println("Grade: " + cows + " " + cowName + ".");
        } else {
            System.out.println("None.");
        }
    }
}
