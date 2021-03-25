import java.util.*;
import java.io.IOException;

public class GameManager {
    private final Scanner sc = new Scanner(System.in);
    private Player player1;
    private Player player2;

    public GameManager() {
        this.player1 = new Player();
        this.player2 = new Player();
    }

    public void start() {
        System.out.println("Player 1, place your ships on the game field\n");
        player1.setupGamefield();
        promptEnterKey();
        System.out.println("\nPlayer 2, place your ships on the game field\n");
        player2.setupGamefield();
        promptEnterKey();

        while (player1.destroyedShips < 5 || player2.destroyedShips < 5) {
            player1.printGameField(player2.getFogOfWar());
            System.out.println("---------------------");
            player1.printGameField(player1.getGameField());
            System.out.println("\nPlayer 1, it's your turn:\n");
            player1.shoot(player2);
            if (player1.destroyedShips  == 5) {
                break;
            }
            promptEnterKey();
            player2.printGameField(player1.getFogOfWar());
            System.out.println("---------------------");
            player2.printGameField(player2.getGameField());
            System.out.println("\nPlayer 2, it's your turn:\n");
            player2.shoot(player1);
            if (player2.destroyedShips  == 5) {
                break;
            }
            promptEnterKey();
        }

        System.out.println("\nYou sank the last ship. You won. Congratulations!");
    }

    public void promptEnterKey() {
        System.out.println("\nPress Enter and pass the move to another player\n");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}