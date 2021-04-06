package tictactoe;

import java.util.Scanner;
import java.util.Arrays;

public class GameManager {
    private final Scanner sc;
    protected char[][] gameField;
    public boolean xWin = false;
    public boolean oWin = false;

    public GameManager() {
        this.sc = new Scanner(System.in);
        this.gameField = new char[3][3];

        for (char[] row: gameField) {
            Arrays.fill(row, ' ');
        }
    }

    public void start() {
        String[] commands;
        while (true) {
            System.out.println("Input command: ");
            commands = sc.nextLine().toLowerCase().split(" ");
            if (commands[0].equals("exit")) {
                return;
            } else if (!commands[0].equals("start") || commands.length != 3 || !commands[1].matches("user|easy|medium|hard") || !commands[2].matches("user|easy|medium|hard")) {
                System.out.println("Bad parameters!");
            } else {
                break;
            }
        }

        Player player1 = null;
        Player player2 = null;

        for (int i = 1; i < 3; i++) {
            switch (commands[i]) {
                case "user":
                    if (i != 2) {
                        player1 = new User('X', gameField);
                        break;
                    } else {
                        player2 = new User('O', gameField);
                    }
                    break;
                case "easy":
                    if (i != 2) {
                        player1 = new Easy('X', gameField);
                        break;
                    } else {
                        player2 = new Easy('O', gameField);
                    }
                    break;
                case "medium":
                    if (i != 2) {
                        player1 = new Medium('X', gameField);
                        break;
                    } else {
                        player2 = new Medium('O', gameField);
                    }
                    break;
                case "hard":
                    if (i != 2) {
                        player1 = new Hard('X', gameField);
                    } else {
                        player2 = new Hard('O', gameField);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + commands[1]);
            }
        }

        printGameField();

        while (isMovesLeft()) {
            player1.makeMove();
            printGameField();
            checkWinner();

            if (xWin) {
                System.out.println("X wins");
                break;
            } if (oWin) {
                System.out.println("O wins");
                break;
            } else if (!isMovesLeft()) {
                System.out.println("Draw");
                break;
            }

            player2.makeMove();
            printGameField();
            checkWinner();

            if (xWin) {
                System.out.println("X wins");
                break;
            } if (oWin) {
                System.out.println("O wins");
                break;
            } else if (!isMovesLeft()) {
                System.out.println("Draw");
                break;
            }
        }
    }

    public void printGameField() {
        System.out.println("---------");
        for (int i = 0; i < gameField.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < gameField[i].length; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void checkWinner() {
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][0] == 'X' && gameField[i][1] == 'X' && gameField[i][2] == 'X') {
                xWin = true;
                return;
            } else if (gameField[i][0] == 'O' && gameField[i][1] == 'O' && gameField[i][2] == 'O') {
                oWin = true;
                return;
            } else if (gameField[0][i] == 'X' && gameField[1][i] == 'X' && gameField[2][i] == 'X') {
                xWin = true;
                return;
            } else if (gameField[0][i] == 'O' && gameField[1][i] == 'O' && gameField[2][i] == 'O') {
                oWin = true;
                return;
            }
        }

        if (gameField[0][0] == 'X' && gameField[1][1] == 'X' && gameField[2][2] == 'X') {
            xWin = true;
        } else if (gameField[0][0] == 'O' && gameField[1][1] == 'O' && gameField[2][2] == 'O') {
            oWin = true;
        } else if (gameField[0][2] == 'X' && gameField[1][1] == 'X' && gameField[2][0] == 'X') {
            xWin = true;
        } else if (gameField[0][2] == 'O' && gameField[1][1] == 'O' && gameField[2][0] == 'O') {
            oWin = true;
        }
    }

    public boolean isMovesLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }
}
