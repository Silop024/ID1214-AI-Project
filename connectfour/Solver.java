package connectfour;

import java.util.List;

public class Solver
{
    //Start from the center, move outward for max efficiency
    private final int[] optimalMoveOrder = {3, 2, 4, 1, 5, 0, 6};

    public Move move = new Move(3, -21);

    private final int maxEvalTimeSeconds = 10;
    private long startTimeMillis = 0;


    public Solver() {}

    public Move minimax(Node node, int depth, boolean isMaximizing, int alpha, int beta)
    {
        List<Node> children = node.computeAndGetChildren();

        if (depth == 0 ||
                children.size() == 0 ||
                (System.currentTimeMillis() - startTimeMillis) / 1000 >= maxEvalTimeSeconds) {
            return node.move;
        }

        if (isMaximizing) {
            Move maxMove = new Move(3, -21);

            for (Node child : children) {
                Move currentMove = minimax(child, depth - 1, false, alpha, beta);

                if (currentMove.score() > maxMove.score()) {
                    maxMove = child.move;
                }

                alpha = Math.max(alpha, maxMove.score());

                if (beta <= alpha) {
                    break;
                }
            }
            return maxMove;
        } else {
            Move minMove = new Move(3, 21);

            for (Node child  : children) {
                Move currentMove = minimax(node, depth - 1, true, alpha, beta);

                if (currentMove.score() < minMove.score()) {
                    minMove = child.move;
                }

                beta = Math.min(beta, minMove.score());

                if (beta <= alpha) {
                    break;
                }
            }
            return minMove;
        }
    }

    public int solve(Board board)
    {
        startTimeMillis = System.currentTimeMillis();

        // This win check used in case it didn't see a win for some reason
        //for (int i = 0; i < 7; i++)
        //    if (board.isPlayable(i) && board.isWinningMove(i)) return i;

        // Lose check used if the AI for some reason is very dumb
        // and doesn't see an imminent loss.
        //int loseCheck = board.opponentWinning();
        //if (loseCheck != -1) {
        //    return loseCheck;
        //}

        //In the end we get the score of the move and the move
        Node root = new Node(board, true);
        Move move = minimax(root, 13, true, -21, 21);
        return move.col();
    }

    public int heuristic(Node node)
    {
        return (43 - node.numberOfMoves) / 2;
    }
}
