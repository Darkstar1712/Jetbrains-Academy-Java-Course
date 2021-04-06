package tictactoe;

public class User extends Player {
    public User(char player, char[][] gameField) {
        super(player, gameField);
    }

    @Override
    public char[][] makeMove() {
        int x;
        int y;

        while (true) {
            System.out.println("Enter the coordinates: ");

            try {
                x = sc.nextInt();
                y = sc.nextInt();

                if (x < 0 || x > 3 || y < 0 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (gameField[x - 1][y - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    gameField[x - 1][y - 1] = player;
                    return gameField;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
            }
        }
    }
}
