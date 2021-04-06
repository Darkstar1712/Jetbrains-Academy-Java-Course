package tictactoe;

import java.util.Scanner;

public class Player {
    protected Scanner sc;
    protected char player;
    protected char[][] gameField;

    protected Player(char player, char[][] gameField) {
        this.sc = new Scanner(System.in);
        this.player = player;
        this.gameField = gameField;
    }

    char[][] makeMove(){
        return gameField;
    }
}
