package tictactoe;

public class Hard extends Player {
    private char opponent;

    public Hard(char player, char[][] gameField) {
        super(player, gameField);

        if(player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }
    }

    @Override
    public char[][] makeMove() {
        System.out.println("Making move level \"hard\"");
        int[] coords = findBestMove(gameField);

        gameField[coords[0]][coords[1]] = player;
        return gameField;
    }

    public int evaluateGameState(char[][] gameField) {
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0] == player && gameField[i][1] == player && gameField[i][2] == player) {
                return 10;
            } else if (gameField[i][0] == opponent && gameField[i][1] == opponent && gameField[i][2] == opponent) {
                return -10;
            } else if (gameField[0][i] == player && gameField[1][i] == player && gameField[2][i] == player) {
                return 10;
            } else if (gameField[0][i] == opponent && gameField[1][i] == opponent && gameField[2][i] == opponent) {
                return -10;
            }
        }

        if (gameField[0][0] == player && gameField[1][1] == player && gameField[2][2] == player) {
            return 10;
        } else if (gameField[0][0] == opponent && gameField[1][1] == opponent && gameField[2][2] == opponent) {
            return -10;
        } else if (gameField[0][2] == player && gameField[1][1] == player && gameField[2][0] == player) {
            return 10;
        } else if (gameField[0][2] == opponent && gameField[1][1] == opponent && gameField[2][0] == opponent) {
            return -10;
        }
        return 0;
    }

    public int minimax(char[][] gameField, int depth, boolean isMax) {
        int score = evaluateGameState(gameField);

        if (score == 10) {
            return 10;
        } else if (score == -10) {
            return -10;
        } else if (!isMovesLeft(gameField)) {
            return 0;
        }

        if (isMax) {
            int best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameField[i][j] == ' ') {
                        gameField[i][j] = player;

                        best = Math.max(best, minimax(gameField, depth + 1, !isMax));

                        gameField[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameField[i][j] == ' ') {
                        gameField[i][j] = opponent;

                        best = Math.min(best, minimax(gameField, depth + 1, !isMax));

                        gameField[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    public int[] findBestMove(char[][] gameField) {
        int bestMove = -1000;
        int bestMoveX = -1;
        int bestMoveY = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == ' ') {
                    gameField[i][j] = player;

                    int move = minimax(gameField, 0, false);

                    gameField[i][j] = ' ';

                    if (move > bestMove) {
                        bestMoveX = i;
                        bestMoveY = j;
                        bestMove = move;
                    }
                }
            }
        }
        return new int[] {bestMoveX, bestMoveY};
    }

    public boolean isMovesLeft(char[][] gameField) {
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
