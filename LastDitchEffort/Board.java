package connect4;

public class Board
{
    private final int WIDTH  = 7;
    private final int HEIGHT = 6;

    private int[] columnMoves = new int[WIDTH];
	private int moves;
    private int[][] board = new int[WIDTH][HEIGHT];

    public Board()
    {
		for (int i = 0; i < WIDTH;i++ )
		{
            for (int j = 0; j < HEIGHT; j++)
                this.board[i][j] = 0;
            this.columnMoves[i] = 0;
		}
		this.moves = 0;
    }

    public Board(final Board b)
    {
		for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
			{
				this.board[i][j] = b.board[i][j];
				if(b.board[i][j] != 0)
					this.columnMoves[i]++;
			}
        this.moves = b.moves;
    }
    
    public Board(int[][] board, int[] columnMoves, int moves) 
    {
		
	}

	public Board copy(Board b)
    {
    	int[][] board = new int[WIDTH][HEIGHT];
    	int[] columnMoves = new int[WIDTH];
    	for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
			{
				board[i][j] = b.board[i][j];
				if(b.board[i][j] != 0)
					columnMoves[i]++;
			}
    	int moves = b.getMoves();
    	Board t = new Board(board, columnMoves, moves);
    	return t;  	
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
		   && board[column][row - 1] == player
		   && board[column][row - 2] == player
		   && board[column][row - 3] == player)
			return true;

		for(int yDirection = -1; yDirection <= 1; yDirection++)
		{
			int matches = 0;
			for(int xDirection = -1; xDirection <= 1; xDirection += 2)
				for(int x = column + xDirection, y = columnMoves[column] + xDirection * yDirection; x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && board[x][y] == player; matches++)
				{
					x += xDirection;
					y += xDirection * yDirection;
				}
			if(matches >= 3)
				return true;
		}
		return false;
    }

    public void addPiece(int column)
    {
        board[column][columnMoves[column]] = 1 + (moves % 2);
		moves++;
		columnMoves[column]++;
    }

    public int getMoves()
    {
        return moves;
    }

    public int getColumnMoves(int column)
	{
		return columnMoves[column];
	}
    
    public int[][] getBoard()
    {
    	return board;
    }
    
    public void setBoard(int[][] b)
    {
    	for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
				this.board[i][j] = b[i][j];
    }
    
    public String  toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				sb.append("|");
				if (board[i][j] == 0)
					sb.append("_");
				else
					sb.append(board[i][j]);
				sb.append("|");
//				System.out.println(grid[j][i]);
			}
			sb.append('\n');
		}

		return String.valueOf(sb);
	}

}
