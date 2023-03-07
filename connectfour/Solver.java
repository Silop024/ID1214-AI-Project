package connectfour;

public class Solver
{
    //Start from the center, move outward for max efficiency
    private final int[] moveOrder = {3, 2, 4, 1, 5, 0, 6};

    //Evaluation of the position in general, not the move
    public int evaluation;

    //How many nodes it traversed
    public int nodesChecked;

    //Score, column, depth
    public int[] move = {-21, 3, 13};

    private final int maxEvalTimeSeconds = 10;
    private long startTimeMillis = 0;


    public Solver() {}

    public int minimax(Board parent, int depth, boolean isMaximizing, int alpha, int beta)
    {
        nodesChecked++;

        if (depth == 0 || parent.getMoves() == 42) return 0;

        int score;
        if (isMaximizing) {
            score = -21;
            int[] scores = {-21, -21, -21, -21, -21, -21, -21};
            for (int col1 : moveOrder) {
                if (parent.isPlayable(col1)) {
                    if (parent.isWinningMove(col1)) {
                        return (43 - parent.getMoves()) / 2;
                    }
                    Board child = new Board(parent);
                    child.addPiece(col1);
                    score = Math.max(score, minimax(child, depth - 1, false, alpha, beta));
                    scores[col1] = score;

                    if (depth == 13) {
                        move[1] = moveOrder[0];
                        move[0] = scores[3];
                        for (int col2 : moveOrder) {
                            if (scores[col2] > move[0] && parent.isPlayable(col2)) {
                                move[1] = col2;
                                move[0] = scores[col2];
                            }
                        }
                    }
                    alpha = Math.max(alpha, score);
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
        } else {
            score = 21;
            for (int col : moveOrder) {
                if (parent.isPlayable(col)) {
                    if (parent.isWinningMove(col)) {
                        return -(43 - parent.getMoves()) / 2;
                    }
                    Board child = new Board(parent);
                    child.addPiece(col);

                    score = Math.min(score, minimax(child, depth - 1, true, alpha, beta));
                    beta = Math.min(beta, score);
                    if (beta <= alpha) {
                        break;
                    }
                }

            }
        }
        return score;
    }

    public int solve(Board board)
    {
        startTimeMillis = System.currentTimeMillis();

        nodesChecked = 0;
        // This win check used in case it didn't see a win for some reason
        for (int i = 0; i < 7; i++)
            if (board.isPlayable(i) && board.isWinningMove(i)) return i;

        // Lose check used if the AI for some reason is very dumb
        // and doesn't see an imminent loss.
        int loseCheck = board.opponentWinning();
        if (loseCheck != -1) {
            evaluation = -(42 - board.getMoves()) / 2;
            return loseCheck;
        }

        //In the end we get the score of the move and the move
        evaluation = minimax(board, 13, true, -21, 21);
        return move[1];
    }
}
