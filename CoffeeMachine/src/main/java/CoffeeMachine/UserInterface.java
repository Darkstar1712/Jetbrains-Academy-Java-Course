package CoffeeMachine;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            if (coffeeMachine.getState().equals("MENU")) {
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
            }

            String input = sc.nextLine();

            if (input.equals("exit"))  {
                break;
            } else {
                coffeeMachine.processInput(input);
            }
        }
    }
}