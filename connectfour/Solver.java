package connectfour;

public class Solver
{
	//Start from the center, move outward for max efficiency
    private int[] moveOrder = {3,2,4,1,5,0,6};
    //Not used anymore, was the move
    //private int playedColumn;
    //Evaluation of the position in general, not the move
    public int evaluation;
    //How many nodes it traversed
    public int nodesChecked;
    //Score, column, depth
    public int[] move = {-21, 3, 13};

    
    public Solver() {}

    public int minimax(Board parent, int depth, boolean isMaximizing, int alpha, int beta)
    {
    	//NodesChecked was used as benchmark to see what changes made improvements
    	nodesChecked++;
    	//If max depth is reached or max moves without a winner happened,
    	//return 0 as in we dont knopw if its better or worse for the player
        if(depth == 0 || parent.getMoves() == 42)
            return 0;

        if(isMaximizing)
        {
        	//Score starts at the lowest possible score.
            int score = -21;
            int[] scores = {-21, -21, -21, -21, -21, -21, -21};
            for(int i = 0; i < 7; i++)
            {
            	//Only check playable moves
                if(parent.isPlayable(moveOrder[i]))
                {
                	//If the move is a winning move we return the score
                	//of that move.
                    if(parent.isWinningMove(moveOrder[i]))
                    {
                        return (43 - parent.getMoves()) / 2;
                    }
                    //Simulate the next move by making a dummy board state
                    //and then making a move/evaulating it, all the way down until
                    //maximum depth is found or beta cutoff
                    Board child = new Board(parent);
                    child.addPiece(moveOrder[i]);
                    score = max(score, minimax(child, depth - 1, false, alpha, beta));
                    scores[moveOrder[i]] = score;
                    if(depth == 13) 
                    {
                    	//Base values
                    	move[1] = moveOrder[0];
                    	move[0] = scores[3];
                    	//Goes through all the possible moves at the current position,
                    	//then chooses the one with the highest score.
                    	for(int j = 1; j < 7; j++) 
                    	{
                    		if(scores[moveOrder[j]] > move[0] && parent.isPlayable(moveOrder[j]))
                    		{
                    			move[1] = moveOrder[j];
                            	move[0] = scores[moveOrder[j]];
                    		}
                    	}
                    }
                    //Update alpha
                    alpha = max(alpha, score);
                    //If we find a value greater than or equal to beta, that means
                    //we dont care about searching that node since we already know
                    //a path that will lead to a greater score
                    if(alpha >= beta) 
                    { 
                        break;
                    }
                }
            }
            return score;
        } 
        //Alpha but the opposite. 
        //Difference is here we dont care to save the best move 
        //because this is a representation of the opposing player
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
    	//Wincheck was used in case it didnt see a win for some reason
    	//but it does now
    	for(int i = 0; i < 7; i++)
    		if(board.isPlayable(i) && board.isWinningMove(i))
    			return i;
    	//Losecheck used if the AI for some reason is very dumb
    	//and doesn't see an imminent loss.
    	int loseCheck = board.opponentWinning();
    	if(loseCheck != -1) 
    	{
    		evaluation = -(42 - board.getMoves()) / 2;
    		return loseCheck;
    	}
    	//In the end we get the score of the move and the move
    	evaluation = minimax(board, 13, true, -21, 21);
        return move[1];
    }
}
