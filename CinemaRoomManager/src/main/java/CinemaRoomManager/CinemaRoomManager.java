package CinemaRoomManager;

import java.util.Scanner;
import java.util.Arrays;

public class CinemaRoomManager {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        int rows;
        int seats;
        int totalTickets;
        int soldTickets = 0;
        int currentIncome = 0;
        int totalIncome;
        double percentage = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        String[][] room = createArray(rows, seats);
        totalIncome = calculateTotalIncome(rows, seats);
        totalTickets = rows * seats;


        while (true) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int input = scanner.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    printArray(room, rows, seats);
                    break;
                case 2:
                    currentIncome += buyTicket(room, rows, seats);
                    soldTickets++;
                    percentage = 100.00 / totalTickets * soldTickets;
                    break;
                case 3:
                    System.out.println("Number of purchased tickets: " + soldTickets);
                    System.out.printf("Percentage: " + "%.2f" + "%%%n", percentage);
                    System.out.println("Current income: " + "$" + currentIncome);
                    System.out.println("Total income: " + "$" + totalIncome);
            }
        }
    }

    public static String[][] createArray(int rows, int seats) {
        String[][] room = new String[rows + 1][seats + 1];
        room[0][0] = " ";
        for (int i = 1; i <= rows; i++) {
            room[i][0] = String.valueOf(i);
        }

        for (int i = 1; i <= seats; i++) {
            room[0][i] = String.valueOf(i);
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                room[i][j] = "S";
            }
        }
        return room;
    }

    public static void printArray(String[][] room, int rows, int seats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int buyTicket(String[][] room, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        int price;
        int rowNumber;
        int seatNumber;

        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();

            if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seats) {
                System.out.println("Wrong input!");
                continue;
            }

            if (room[rowNumber][seatNumber].equals("B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }

        if (rows * seats <= 60) {
            price = 10;
        } else {
            if(rowNumber <= rows / 2) {
                price = 10;
            }  else {
                price = 8;
            }
        }

        room[rowNumber][seatNumber] = "B";
        System.out.println("Ticket price: $" + price);
        return price;
    }

    public static int calculateTotalIncome(int rows, int seats) {
        if (rows * seats <= 60) {
            return rows * seats * 10;
        } else {
            if(rows % 2 == 0) {
                return ((rows / 2) * seats * 10) + ((rows / 2) * seats * 8);
            }  else {
                return ((rows / 2) * seats * 10) + (rows - rows / 2) * seats * 8;
            }
        }
    }
}
