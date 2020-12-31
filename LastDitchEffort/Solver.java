package connect4;

public class Solver
{
    private int[] moveOrder = {3,2,4,1,5,0,6};
    private int playedColumn;
    public int evaluation;

    
    public Solver() {}

    public int minimax(Board parent, int depth, boolean isMaximizing)
    {
        if(depth == 0)
            return 0;

        if(isMaximizing)
        {
            int max = -21;
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

                    int score = minimax(child, depth - 1, false);
                    if(score > max)
                    {
                        max = score;
                        playedColumn = moveOrder[i];
                    }
                }
            }
            return max;
        }
        else
        {
            int min = 21;
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
                    
                    int score = minimax(child, depth - 1, true);
                    if(score < min)
                        min = score;
                }

            }
            return min;
        }
    }

    public int solve(Board board)
    {
        evaluation = minimax(board, 7, true);
        return playedColumn;
    }
}
