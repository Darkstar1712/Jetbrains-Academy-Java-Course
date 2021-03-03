package TicTacToe;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] chars = new char[3][3];
        int counter = 1;
        int xWins = 0;
        int oWins = 0;
        int total = 0;

        System.out.println("---------");
        for(int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
                chars[i][j] = ' ';
                System.out.print(chars[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");

        while (true) {
            int coordX = 0;
            int coordY = 0;
            int xCount = 0;
            int oCount = 0;

            System.out.println("Enter the coordinates: ");

            if (scanner.hasNextInt()) {
                coordX = scanner.nextInt();
                coordY = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (coordY < 1 || coordX > 3 || coordY < 1 || coordY > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (chars[coordX - 1][coordY -1] == 'X' || chars[coordX - 1][coordY - 1] == 'O') {
                System.out.println("The cell is occupied! Choose another one!");
                continue;
            }

            if (counter % 2 != 0) {
                chars[coordX - 1][coordY - 1] = 'X';
            } else {
                chars[coordX - 1][coordY - 1] = 'O';
            }

            counter++;

            System.out.println("---------");
            for(int i = 0; i < 3; i++) {
                System.out.print("| ");
                for(int j = 0; j < 3; j++) {
                    System.out.print(chars[i][j] + " ");
                }
                System.out.print("|");
                System.out.println();
            }
            System.out.println("---------");

            for (int i = 0; i < 3; i++) {
                total = 0;
                for (int j = 0; j < 3; j++) {
                    if (chars[i][j] == 'X') {
                        xCount++;
                    } else if (chars[i][j] == 'O') {
                        oCount++;
                    }
                    total += chars[i][j];
                }
                if (total == 264) {
                    xWins++;
                } else if (total == 237) {
                    oWins++;
                }
            }

            for (int i = 0; i < 3; i++) {
                total = 0;
                for (int j = 0; j < 3; j++) {
                    total += chars[j][i];
                }
                if (total == 264) {
                    xWins++;
                } else if (total == 237) {
                    oWins++;
                }
            }

            for (int i = 0, k = 0; i < 3; i++) {
                k += chars[i][i];
                if (k == 264) {
                    xWins++;
                } else if (k == 237) {
                    oWins++;
                }
            }

            for (int i = 0, j = 2, k = 0; i < 3; i++, j--) {
                k += chars[i][j];
                if (k == 264) {
                    xWins++;
                } else if (k == 237) {
                    oWins++;
                }
            }

            if (xCount + oCount == 9 && xWins == 0 && oWins == 0) {
                System.out.println("Draw");
                break;
            } else if (xWins == 1 && oWins == 0) {
                System.out.println("X wins");
                break;
            } else if (oWins == 1 && xWins == 0) {
                System.out.println("O wins");
                break;
            }
        }
    }
}