package tictactoe;

public class Easy extends Player {
    public Easy(char player, char[][] gameField) {
        super(player, gameField);
    }

    @Override
    public char[][] makeMove() {
        int x;
        int y;

        System.out.println("Making move level \"easy\"");

        while (true) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);

            if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                if (gameField[x][y] == ' ') {
                    gameField[x][y] = player;
                    return gameField;
                }
            }
        }
    }
}
