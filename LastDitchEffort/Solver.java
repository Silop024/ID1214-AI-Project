package connect4;

public class Solver
{
    private int[] moveOrder = {3,2,4,1,5,0,6};
    private int playedColumn;
    public int evaluation;
    public int nodesChecked;

    
    public Solver() {}

    public int minimax(Board parent, int depth, boolean isMaximizing, int alpha, int beta)
    {
    	nodesChecked++;
//    	int loseCheck = parent.opponentWinning();
//    	int score = 0;
//    	if(isMaximizing)
//    		score = -21;
//    	else 
//    		score = 21;
//    	
//		if(loseCheck >= 0) 
//    	{
//    		playedColumn = loseCheck;
//    		if(isMaximizing)
//    			score = -(42 - parent.getMoves()) / 2;
//    		else
//    			score = (43 - parent.getMoves()) / 2;
//    	}
        if(depth == 0 || parent.getMoves() == 42)
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
                    if(parent.isWinningMove(moveOrder[i]))
					{
						return -(43 - parent.getMoves()) / 2;
					}
                    Board child = new Board(parent);
                    child.addPiece(moveOrder[i]);
                    
                    score = min(score, minimax(child, depth - 1, true, alpha, beta));
                    beta = min(beta, score);
                    if(beta <= alpha)
                    {
                    	break;
                    }
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
    	nodesChecked = 0;
    	int loseCheck = board.opponentWinning();
    	if(loseCheck != -1) 
    	{
    		evaluation = -(42 - board.getMoves()) / 2;
    		return loseCheck;
    	}
//    	for(int i = 0; i < 7; i++)
//    		if(board.openAlignment(moveOrder[i]) != -1) {System.out.println("found");
//    			return board.openAlignment(moveOrder[i]);}
//    	int min = -(42 - board.getMoves())/2;
//    	int max = (43 - board.getMoves())/2;
//    	while(min < max)
//    	{
//    		int med = min + (max - min)/2;
//    		if(med <= 0 && min/2 < med)
//    			med = min/2;
//    		else if(med >= 0 && max/2 > med)
//    			med = max/2;
//    		int r = minimax(board, 13, true, med, med + 1);
//    		if(r <= med)
//    			max = r;
//    		else
//    			min = r;
//    		evaluation = min;
//    	}
    	evaluation = minimax(board, 12, true, -21, 21);
        return playedColumn;
    }
}
