package tictactoe;

public class Medium extends Player {
    public Medium(char player, char[][] gameField) {
        super(player, gameField);
    }

    @Override
    public char[][] makeMove() {
        System.out.println("Making move level \"medium\"");
        if (player == 'X') {
            for (int i = 0; i < gameField.length; i++) {
                int horizontalTotal = 0;
                int verticalTotal = 0;
                for (int j = 0; j < gameField[i].length; j++) {
                    horizontalTotal += gameField[i][j];
                    verticalTotal += gameField[j][i];
                }

                if (horizontalTotal == 208) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[i][j] == ' ') {
                            gameField[i][j] = 'X';
                            return gameField;
                        }
                    }
                } else if (verticalTotal == 208) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[j][i] == ' ') {
                            gameField[j][i] = 'X';
                            return gameField;
                        }
                    }
                }
            }

            for (int i = 0; i < gameField.length; i++) {
                int horizontalTotal = 0;
                int verticalTotal = 0;
                for (int j = 0; j < gameField[i].length; j++) {
                    horizontalTotal += gameField[i][j];
                    verticalTotal += gameField[j][i];
                }

                if (horizontalTotal == 190) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[i][j] == ' ') {
                            gameField[i][j] = 'X';
                            return gameField;
                        }
                    }
                } else if (verticalTotal == 190) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[j][i] == ' ') {
                            gameField[j][i] = 'X';
                            return gameField;
                        }
                    }
                }
            }

            int primaryDiagonalValue = 0;
            int secondaryDiagonalValue = 0;
            for (int i = 0; i < gameField.length; i++) {
                primaryDiagonalValue += gameField[i][i];
                secondaryDiagonalValue += gameField[i][Math.abs(i - 2)];

                if (primaryDiagonalValue == 208) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][j] == ' ') {
                            gameField[j][j] = 'X';
                            return gameField;
                        }
                    }
                } else if (secondaryDiagonalValue == 208) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][Math.abs(j - 2)] == ' ') {
                            gameField[j][Math.abs(j - 2)] = 'X';
                            return gameField;
                        }
                    }
                } else if (primaryDiagonalValue == 190) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][j] == ' ') {
                            gameField[j][j] = 'X';
                            return gameField;
                        }
                    }
                } else if (secondaryDiagonalValue == 190) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][Math.abs(j - 2)] == ' ') {
                            gameField[j][Math.abs(j - 2)] = 'X';
                            return gameField;
                        }
                    }
                }
            }

            while (true) {
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);

                if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                    if (gameField[x][y] == ' ') {
                        gameField[x][y] = player;
                        return gameField;
                    }
                }
            }
        } else {
            for (int i = 0; i < gameField.length; i++) {
                int horizontalTotal = 0;
                int verticalTotal = 0;

                for (int j = 0; j < gameField[i].length; j++) {
                    horizontalTotal += gameField[i][j];
                    verticalTotal += gameField[j][i];
                }

                if (horizontalTotal == 190) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[i][j] == ' ') {
                            gameField[i][j] = 'O';
                            return gameField;
                        }
                    }
                } else if (verticalTotal == 190) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[j][i] == ' ') {
                            gameField[j][i] = 'O';
                            return gameField;
                        }
                    }
                }
            }

            for (int i = 0; i < gameField.length; i++) {
                int horizontalTotal = 0;
                int verticalTotal = 0;

                for (int j = 0; j < gameField[i].length; j++) {
                    horizontalTotal += gameField[i][j];
                    verticalTotal += gameField[j][i];
                }

                if (horizontalTotal == 208) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[i][j] == ' ') {
                            gameField[i][j] = 'O';
                            return gameField;
                        }
                    }
                } else if (verticalTotal == 208) {
                    for (int j = 0; j < gameField.length; j++) {
                        if (gameField[j][i] == ' ') {
                            gameField[j][i] = 'O';
                            return gameField;
                        }
                    }
                }
            }

            int primaryDiagonalValue = 0;
            int secondaryDiagonalValue = 0;
            for (int i = 0; i < gameField.length; i++) {
                primaryDiagonalValue += gameField[i][i];
                secondaryDiagonalValue += gameField[i][Math.abs(i - 2)];

                if (primaryDiagonalValue == 190) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][j] == ' ') {
                            gameField[j][j] = 'O';
                            return gameField;
                        }
                    }
                } else if (secondaryDiagonalValue == 190) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][Math.abs(j - 2)] == ' ') {
                            gameField[j][Math.abs(j - 2)] = 'O';
                            return gameField;
                        }
                    }
                } else if (primaryDiagonalValue == 208) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][j] == ' ') {
                            gameField[j][j] = 'O';
                            return gameField;
                        }
                    }
                } else if (secondaryDiagonalValue == 208) {
                    for (int j = 0; j < gameField[i].length; j++) {
                        if (gameField[j][Math.abs(j - 2)] == ' ') {
                            gameField[j][Math.abs(j - 2)] = 'O';
                            return gameField;
                        }
                    }
                }
            }

            while (true) {
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);

                if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                    if (gameField[x][y] == ' ') {
                        gameField[x][y] = player;
                        return gameField;
                    }
                }
            }
        }
    }
}
