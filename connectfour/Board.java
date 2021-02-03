package connectfour;

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

	public int opponentWinning()
	{
		moves++;
		for(int column = 0; column < WIDTH; column++)
		{
			if(this.isPlayable(column) && this.isWinningMove(column))
			{
				moves--;
				return column;
			}
		}
		moves--;
		return -1;
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
    	this.board = new int[6][7];
    	for(int i = 0; i < 6; i++)
			for(int j = 0; j < 7; j++)
				this.board[i][j] = b[i][j];
    }
    
    public String  toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = HEIGHT - 1; i >= 0; i--) {
			for (int j = 0; j < WIDTH; j++) {
				sb.append("|");
				if (board[j][i] == 0)
					sb.append("_");
				else
					sb.append(board[j][i]);
				sb.append("|");
			}
			sb.append('\n');
		}

		return String.valueOf(sb);
	}


	public boolean hasPiece(int row, int column) {
    	return board[row][column] !=0;
	}

	public int getPiece(int row, int column) {
    	return board[column][row];
	}

	public boolean winner(Board board,int player){

		for (int row = 0; row < HEIGHT ; row++)
			for (int column = 0; column < WIDTH - 3; column++) {


				if (board.getPiece(row, column) == player &&
						board.getPiece(row, column + 1) == player &&
						board.getPiece(row, column + 2) == player &&
						board.getPiece(row, column + 3) == player
				) return true;
			}



		for (int row = 0; row < HEIGHT - 3 ; row++)
			for (int column = 0; column < WIDTH; column++) {
				if (board.getPiece(row, column) == player &&
						board.getPiece(row + 1, column) == player &&
						board.getPiece(row + 2, column) == player &&
						board.getPiece(row + 3, column) == player
				)
					return true;
			}


		// diagonal matches
		for (int row = 3; row < HEIGHT ; row++)
			for (int column = 0; column < WIDTH - 3; column++) {
				if (board.getPiece(row, column)  == player &&
						board.getPiece(row - 1, column + 1)  == player &&
						board.getPiece(row - 2, column + 2)  == player &&
						board.getPiece(row - 3, column + 3)  == player
				)
					return true;
			}

		for (int row = 0; row < HEIGHT - 3; row++)
			for (int column = 0; column < WIDTH - 3; column++) {
				if (board.getPiece(row, column) == player &&
						board.getPiece(row + 1, column + 1) == player  &&
						board.getPiece(row + 2, column + 2) == player &&
						board.getPiece(row + 3, column + 3) == player
				)
					return true;
			}







		return false;
	}


}
