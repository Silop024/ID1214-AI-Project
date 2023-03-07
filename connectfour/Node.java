package connectfour;

import java.util.List;

public class Node
{
    /**
     * The parent node in the game tree
     */
    public Node parent;

    /**
     * The child nodes, found one level below in the game tree
     */
    public List<Node> children;

    /**
     * The current state of the game
     */
    public Board state;

    /**
     * Number of moves played so far
     */
    public int numberOfMoves;

    /**
     * The action that led to this node in the game tree
     */
    public Move move;

    /**
     * The depth at which the node exists in the game tree
     */
    public int depth;

    /**
     * The player whose turn it is in this node, true if player 1.
     */
    public boolean isMaximizing;

    public Node(Board state, boolean isMaximizing)
    {
        this.parent = null;
        this.move = null;
        this.depth = 0;

        this.state = state;
        this.isMaximizing = isMaximizing;
        this.numberOfMoves = state.getMoves();
    }

    public Node(Board state, Move move, Node parent)
    {
        this.state = state;
        this.move = move;
        this.parent = parent;
        this.depth = parent.depth + 1;
        this.isMaximizing = !parent.isMaximizing;
        this.numberOfMoves = parent.numberOfMoves + 1;
    }

    private void addChild(Board state, Move move)
    {
        children.add(new Node(state, move, this));
    }

    public boolean canWin()
    {
        for (int i = 0; i < 7; i++) {
            if (state.isWinningMove(i)) return true;
        }
        return false;
    }

    public List<Node> computeAndGetChildren()
    {
        final int[] optimalMoveOrder = {3, 2, 4, 1, 5, 0, 6};

        
    }
}
