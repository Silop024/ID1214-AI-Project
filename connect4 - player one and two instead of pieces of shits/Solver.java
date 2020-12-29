package com.ai.project;


public class Solver 
{
	private Position p;
	private int[] moveOrder = new int[Position.WIDTH];
	private int playedColumn;
	
	public Solver(Position p)
	{
		this.p = p;
		this.playedColumn = 0;
		for(int i = 0; i < Position.WIDTH; i++)
			this.moveOrder[i] = Position.WIDTH/2 + (1-2*(i%2))*(i+1)/2;
	}
	
	public int negamax(Position p, int alpha, int beta)
	{
		//System.out.println("minmaxing");
		//System.out.println("alpha = " + alpha + " beta = " + beta);
		//Each player has 21 moves, thus if there has been 42 moves
		//without a win it's a draw, which is 0 (meaning neither negative
		//or positive outcome for either player).
		//System.out.println("Draw? " + (p.getMoves() == 42));
		if(p.getMoves() == 42)
			return 0;
		
		//Check if curent player can win in the next move
		for(int column = 0; column < Position.WIDTH; column++)
		{
			//If the current column is playable and it's the winning move
			//Score is the number of moves before end.
			if(p.isPlayable(moveOrder[column]) && p.isWinningMove(moveOrder[column]))
			{
				System.out.println("Is winning");
				this.playedColumn = moveOrder[column];
				return (43 - p.getMoves()) / 2;
			}
		}
		
		
		int max = 22 - (p.getMoves()/2 + 1 + p.getMoves()%2);
		//System.out.println("Max = " + max);
		
		if(beta > max)
		{
			beta = max;
			if(alpha >= beta)
			{
				return beta;
			}
		}
		
		//Compute the scores of all possible next moves
		for(int column = 0; column < Position.WIDTH; column++)
		{
			//Only look at the possible moves
			if(p.isPlayable(moveOrder[column]))
			{
				//Get the perspective of the opponent, recersivly going
				//down the game tree.
				Position tempPos = new Position(p);
				//System.out.println("Playing move " + (tempPos.getMoves() + 1) + " for player " + (1 + tempPos.getMoves() % 2) + " at column " + column);
				tempPos.play(moveOrder[column]);
				int score = -negamax(tempPos, -beta, -alpha);
				
				if(score >= beta)
				{
					this.playedColumn = moveOrder[column];
					return score;
				}
				if(score > alpha)
				{
					alpha = score;
				}
			}
		}
		System.out.println("alpha = " + alpha + " beta = " + beta);
		return alpha;
	}
	
	public int solve(Position p)
	{
		System.out.println("Solving shit");
		negamax(p, -21, 21);
		p.reset();
		return this.playedColumn;
	}
	
	public int getMove()
	{
		return this.playedColumn;
	}
}
