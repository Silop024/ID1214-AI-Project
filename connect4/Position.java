package connect4;


import java.util.Arrays;

public class Position
{
	public static final int WIDTH = 7;
	public static final int HEIGHT = 6;
	
	private final int[][] grid;
	private final int[] columnMoves;
	private int moves;


	public Position(Position p)
	{
		this.grid = p.grid;
		this.columnMoves = p.columnMoves;
		this.moves = p.moves;
	}
	
	public Position(int[][] grid, int[]columnMoves, int moves)
	{
		this.grid = grid;
		this.columnMoves = columnMoves;
		this.moves = moves;
	}

	public Position(Board.Piece[][] ofShit)
	{
		int [][] grid = new int[WIDTH][HEIGHT];
		int[] colMoves = new int[7];
		int mov = 0;
		for(int i = 0; i < HEIGHT; i++)
			for (int j = 0; j < WIDTH; j++){
				if(ofShit[i][j] == null)
					grid[j][i] = 0;
				else {
					if (ofShit[i][j].getColor().equals("R"))
						grid[j][i] = 1;
					if (ofShit[i][j].getColor().equals("Y"))
						grid[j][i] = 2;
					colMoves[j]++;
					mov++;
				}
//				System.out.println(grid[j][i]);
			}
		this.grid = grid;
		this.columnMoves = colMoves;
		this.moves = mov;
	}



	public String  toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < HEIGHT;i++) {
			for (int j = 0; j < WIDTH; j++) {
				sb.append("|");
				if (grid[j][i] == 0)
					sb.append("_");
				else
					sb.append(grid[j][i]);
				sb.append("|");
//				System.out.println(grid[j][i]);
			}
			sb.append('\n');
		}

		return String.valueOf(sb);
	}


	public boolean isPlayable(int column)
	{
		return columnMoves[column] < HEIGHT;
	}
	
	public boolean isWinningMove(int column)
	{
		int player = 1 + (moves % 2);
		int row = columnMoves[column];

		if(row >= 3 
		   && grid[column][row - 1] == player
		   && grid[column][row - 2] == player
		   && grid[column][row - 3] == player)
			return true;
		
		for(int yDirection = -1; yDirection <= 1; yDirection++)
		{
			int matches = 0;
			for(int xDirection = -1; xDirection <= 1; xDirection += 2)
				for(int x = column + xDirection, y = columnMoves[column] + xDirection * yDirection; x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && grid[x][y] == player; matches++)
				{
					x += xDirection;
					y += xDirection * yDirection;
				}
			if(matches >= 3)
				return true;
		}
		return false;
		
//		grid[column][row] = player;
//
//		//Check column for vertical alignment, don't need to check the rows above current because it's impossible to place a disc below
//		//any alreayDirection played discs. Only need to check the 3 below it because that's the only way to get a vertical alignment.
//
//		
//		//Check horizontal alignment		
//		int x = column - 3;
//		if(column < 3)
//			x = 0;
//		
//		int matches = 0;
//
//		System.out.println("CHECKING HORIZONTAL MATCHES");
//		while(x < WIDTH)
//		{			
//			if(grid[x][row] == player)
//				matches++;
//			
//			if(matches == 4)
//				return true;
//			else if(matches != 4 && grid[x][row] != player)
//				matches = 0;
//			System.out.println("column " + x + " row " + row + " matches " + matches);
//			x++;
//		}
//		matches = 0;
//		
//		//Check diagonal matches going up from the left
//		int y = row - 3;
//		if(column < 3)
//			y = row - column;
//		if(y < 0)
//			y = 0;
//		x = column - row;
//		if(x < 0)
//			x = 0;
//		
//		System.out.println("CHECKING DIAGONAL MATCHES LEFT TO RIGHT");
//		while(x < WIDTH && y < HEIGHT && !((column > 4 && row < 2) || (column < 2 && row > 3)))
//		{
//			if(grid[x][y] == player)
//				matches++;
//			
//			if(matches == 4)
//				return true;
//			else if(matches != 4 && grid[x][y] != player)
//				matches = 0;
//			
//			System.out.println("column " + x + " row " + y + " matches " + matches);
//			y++;
//			x++;
//		}
//		matches = 0;
//		
//		
//		//Check diagonal matches going down from the right
//		y = row - 3;
//		if(column < 3)
//			y = row + column - 6;
//		if(y < 0)
//			y = 0;
//		x = column + row;
//		if(x > 6)
//			x = 6;
//		
//		System.out.println("CHECKING DIAGONAL MATCHES RIGHT TO LEFT");
//		
//		while(x >= 0 && y < HEIGHT && !((column < 2 && row < 2) || (column > 3 && row > 4)))
//		{
//			if(grid[x][y] == player)
//				matches++;
//			
//			if(matches == 4)
//				return true;
//			else if(matches != 4 && grid[x][y] != player)
//				matches = 0;
//			
//			System.out.println("column " + x + " row " + y + " matches " + matches);
//			y++;
//			x--;
//		}
//		
//		grid[column][row] = 0;
//		return false;
	}
	
	
	public void play(int column)
	{
		grid[column][columnMoves[column]] = 1 + (moves % 2);
		moves++; 
		columnMoves[column]++;
	}
	
	public int getMoves()
	{
		return moves;
	}

	public void reset()
	{
		for(int i = 0; i < WIDTH; i++)
			columnMoves[i] = 0;
	}

}