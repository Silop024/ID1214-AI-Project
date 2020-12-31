package connect4;

public class Solver
{
    private int[] moveOrder = {3,2,4,1,5,0,6};
    private int playedColumn;
    public int evaluation;
	private Board solverBoard;

    public Solver(Board board)
    {
    	this.solverBoard = board;
        this.playedColumn = 0;
        this.moveOrder[0] = 3;
        this.moveOrder[1] = 2;
        this.moveOrder[2] = 4;
        this.moveOrder[3] = 1;
        this.moveOrder[4] = 5;
        this.moveOrder[5] = 0;
        this.moveOrder[6] = 6;
        this.evaluation = 0;
    }
    
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

    public int solve()
    {
        evaluation = minimax(solverBoard, 42 - solverBoard.getMoves(), true);
        return playedColumn;
    }
}
