import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private final Scanner sc = new Scanner(System.in);
    private char[][] gameField;
    private char[][] fogOfWar;
    public Ship[] ships;
    public int destroyedShips = 0;

    public Player() {
        gameField = createGameField();
        fogOfWar = createGameField();
        ships = new Ship[5];
    }

    public char[][] getGameField() {
        return this.gameField;
    }

    public char[][] getFogOfWar() {
        return this.fogOfWar;
    }

    public char[][] createGameField()  {
        char[][] gameField = new char[10][10];

        for (char[] row: gameField) {
            Arrays.fill(row, '~');
        }
        return gameField;
    }

    public void setupGamefield() {
        ships[0] =  new Ship("Aircraft Carrier", 5);
        ships[1] =  new Ship("Battleship", 4);
        ships[2] =  new Ship("Submarine", 3);
        ships[3] =  new Ship("Cruiser", 3);
        ships[4] =  new Ship("Destroyer", 2);

        String str = "\nEnter the coordinates of the %s (%d cells):\n\n> ";

        printGameField(gameField);

        for (int i = 0; i < 5; i++) {
            System.out.printf(str, ships[i].getName(), ships[i].getSize());
            addShip(ships[i].getName(), ships[i].getSize());
            printGameField(gameField);
        }
    }

    public void printGameField(char[][] array) {
        String[] numbers = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        char[] letters = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'j'};

        System.out.printf("\n  %s", String.join(" ", numbers));

        for (int i = 0; i < 10; i++) {
            System.out.printf("\n%c ", letters[i]);

            for (char a : array[i]) {
                System.out.printf("%c ", a);
            }
        }
        System.out.println();
    }

    public void addShip(String name, int size) {
        boolean isHorizontal;
        boolean isVertical;
        char startY;
        char endY;
        int startX;
        int endX;
        int length;
        int index;
        String[] coord;

        coord = getCoord();
        startY = coord[0].charAt(0);
        startX = Integer.parseInt(coord[1]);

        coord = getCoord();
        endY = coord[0].charAt(0);
        endX = Integer.parseInt(coord[1]);

        isHorizontal = startY == endY;
        isVertical = startX == endX;

        if (isHorizontal) {
            length = Math.abs(startX - endX) + 1;
            if (length == size) {
                index = startX < endX ? startX - 1 : endX - 1;
                if (checkShipPlacement(startY, startX, length, index, true)) {
                    for (int i = 0; i < size; i++) {
                        gameField[startY - 'A'][index++] = 'O';
                    }

                    for(int j = 0; j < ships.length; j++) {
                        if (ships[j].getName().equals(name)) {
                            if (startX > endX) {
                                int temp = startX;
                                startX = endX;
                                endX = temp;
                            }
                            String start = startY + "" + startX;
                            String end = endY + "" + endX;
                            ships[j].setStartCoord(start.split("", 2));
                            ships[j].setEndCoord(end.split("", 2));
                        }
                    }
                } else {
                    System.out.print("\nError! You placed it too close to another one. Try again:\n");
                    addShip(name, size);
                }
            } else {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n", name);
                addShip(name, size);
            }
        } else if (isVertical) {
            length = Math.abs(startY - endY) + 1;
            if (length == size) {
                index = startY < endY ? startY - 'A' : endY - 'A';
                if (checkShipPlacement(startY, startX, length, index, false)) {
                    for (int i = 0; i < size; i++) {
                        gameField[index++][startX - 1] = 'O';
                    }

                    for(int j = 0; j < ships.length; j++) {
                        if (ships[j].getName().equals(name)) {
                            if (startY > endY) {
                                char temp = startY;
                                startY = endY;
                                endY = temp;
                            }
                            String start = startY + "" + startX;
                            String end = endY + "" + endX;
                            ships[j].setStartCoord(start.split("", 2));
                            ships[j].setEndCoord(end.split("", 2));
                        }
                    }
                } else {
                    System.out.print("\nError! You placed it too close to another one. Try again:\n");
                    addShip(name, size);
                }
            } else {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n", name);
                addShip(name, size);
            }
        } else {
            System.out.print("\nError! Wrong ship location! Try again:\n");
            addShip(name, size);
        }
    }

    public String[] getCoord() {
        while (true) {
            String input = sc.next().toUpperCase();
            if (input.matches("^(\\s*[A-J][0-9]|[A-J]10)$")) {
                return input.split("", 2);
            } else {
                System.out.print("\nError! Wrong ship location! Try again:\n");
                sc.nextLine();
                continue;
            }
        }
    }

    private boolean checkShipPlacement(char startY, int startX, int length, int fieldIndex, boolean isHorizontal) {
        if (isHorizontal) {
            try {
                if (gameField[startY - 'A'][fieldIndex - 1] == 'O') {
                    return false;
                }
            } catch (IndexOutOfBoundsException ignored) {

            }
            for (int i = 0; i < length; i++) {
                if (gameField[startY - 'A'][fieldIndex++] == 'O') {
                    return false;
                }
            }
            try {
                if (gameField[startY - 'A'][fieldIndex] == 'O') {
                    return false;
                }
            } catch (IndexOutOfBoundsException ignored) {

            }
        } else {
            try {
                if (gameField[fieldIndex - 1][startX - 1] == 'O') {
                    return false;
                }
            } catch (IndexOutOfBoundsException ignored) {

            }
            for (int i = 0; i < length; i++) {
                if (gameField[fieldIndex++][startX - 1] == 'O') {
                    return false;
                }
            }
            try {
                if (gameField[fieldIndex][startX - 1] == 'O') {
                    return false;
                }
            } catch (IndexOutOfBoundsException ignored) {

            }
        }
        return true;
    }

    public void shoot(Player player) {
        String[] coord;
        String str = null;
        char y;
        int x;
        char target;

        coord = getCoord();
        y = coord[0].charAt(0);
        x = Integer.parseInt(coord[1]);

        try {
            target = player.gameField[y - 'A'][x - 1];
            if (target == 'O') {
                player.gameField[y - 'A'][x - 1] = 'X';
                player.fogOfWar[y - 'A'][x - 1] = 'X';

                for(int i = 0; i < player.ships.length; i++) {
                    if (String.valueOf(y).matches("^([" + player.ships[i].getStartCoord(0) + "-" + player.ships[i].getEndCoord(0) + "])$") && String.valueOf(x - 1).matches("^([" + (Integer.parseInt(player.ships[i].getStartCoord(1)) - 1) + "-" + (Integer.parseInt(player.ships[i].getEndCoord(1)) - 1) + "])$")) {
                        player.ships[i].increaseHitCount();
                        if (player.ships[i].getHitCount() == player.ships[i].getSize()) {
                            player.ships[i].hitCount = 0;
                            destroyedShips++;
                            if (destroyedShips == 5) {
                                return;
                            }
                            str = "\nYou sank a ship!:\n";
                        } else {
                            str = "\nYou hit a ship!:\n";
                        }
                    }
                }

            } else if (target == 'X') {
                str = "\nYou hit a ship! Try again:\n";
            }else {
                player.gameField[y - 'A'][x - 1] = 'M';
                player.fogOfWar[y - 'A'][x - 1] = 'M';
                str = "\nYou missed!\n";
            }
            System.out.printf("\n%s", str);
        } catch (IndexOutOfBoundsException e) {
            System.out.print("\nError! You entered the wrong coordinates! Try again:\n\n> ");
            shoot(player);
        }
    }
}