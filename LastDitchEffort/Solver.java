package connect4;

public class Solver
{
    private int[] moveOrder = {3,2,4,1,5,0,6};
    private int playedColumn;
    public int evaluation;

    
    public Solver() {}

    public int minimax(Board parent, int depth, boolean isMaximizing, int alpha, int beta)
    {
        if(depth == 0)
            return 0;

        if(isMaximizing)
        {
            int score = -21;
            for(int i = 0; i < 7; i++)
            {
                if(parent.isPlayable(moveOrder[i]))
                {
                    if(parent.isWinningMove(moveOrder[i]))
                    {
                        return (43 - parent.getMoves()) / 2;
                    }
                    Board child = new Board(parent);
                    child.addPiece(moveOrder[i]);

                    score = max(score, minimax(child, depth - 1, false, alpha, beta));
                    alpha = max(alpha, score);
                    if(alpha >= beta) 
                    { 
                    	playedColumn = moveOrder[i];
                        break;
                    }
                }
            }
            return score;
        }
        else
        {
            int score = 21;
            for(int i = 0; i < 7; i++)
            {
                if(parent.isPlayable(moveOrder[i]))
                {
                    if(parent.isWinningMove(i))
					{
						return -(43 - parent.getMoves()) / 2;
					}
                    Board child = new Board(parent);
                    child.addPiece(moveOrder[i]);
                    
                    score = min(score, minimax(child, depth - 1, true, alpha, beta));
                    beta = min(beta, score);
                    if(beta <= alpha)
                    	break;
                }

            }
            return score;
        }
    }
    
    public int max(int a, int b)
    {
    	if(a > b)
    		return a;
    	return b;
    }
    
    public int min(int a, int b)
    {
    	if(a < b)
    		return a;
    	return b;
    }

    public int solve(Board board)
    {
        evaluation = minimax(board, 12, true, -21, 21);
        return playedColumn;
    }
}
