package connectfour;

public class Board
{
    private final int WIDTH = 7;
    private final int HEIGHT = 6;

    private final int[] columnMoves = new int[WIDTH];
    private final int[][] board = new int[WIDTH][HEIGHT];

    private int moves;

    public Board()
    {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++)
                this.board[i][j] = 0;
            this.columnMoves[i] = 0;
        }
        this.moves = 0;
    }

    public Board(final Board b)
    {
        /*this.board = b.getBoard().clone();
        this.moves = b.moves;
        this.columnMoves = b.columnMoves.clone();*/

        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++) {
                this.board[i][j] = b.board[i][j];

                if (b.board[i][j] != 0) this.columnMoves[i]++;
            }
        this.moves = b.moves;
    }

    public boolean isPlayable(int column)
    {
        return columnMoves[column] < HEIGHT;
    }

    public boolean isWinningMove(int column)
    {
        int player = 1 + (moves % 2);
        int row = columnMoves[column];

        if (row >= 3 && board[column][row - 1] == player && board[column][row - 2] == player && board[column][row - 3] == player)
            return true;

        for (int yDirection = -1; yDirection <= 1; yDirection++) {
            int matches = 0;
            for (int xDirection = -1; xDirection <= 1; xDirection += 2)
                for (int x = column + xDirection, y = columnMoves[column] + xDirection * yDirection; x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && board[x][y] == player; matches++) {
                    x += xDirection;
                    y += xDirection * yDirection;
                }
            if (matches >= 3) return true;
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
        for (int column = 0; column < WIDTH; column++) {
            if (this.isPlayable(column) && this.isWinningMove(column)) {
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

    public int[][] getBoard()
    {
        return board;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = HEIGHT - 1; i >= 0; i--) {
            for (int j = 0; j < WIDTH; j++) {
                sb.append("|");
                if (board[j][i] == 0) sb.append("_");
                else sb.append(board[j][i]);
                sb.append("|");
            }
            sb.append('\n');
        }
        return String.valueOf(sb);
    }


    public boolean hasPiece(int row, int column)
    {
        return board[row][column] != 0;
    }

    public int getPiece(int row, int column)
    {
        return board[column][row];
    }

    public boolean winner(Board board, int player)
    {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH - 3; column++) {
                if (board.getPiece(row, column)
                        == player && board.getPiece(row, column + 1)
                        == player && board.getPiece(row, column + 2)
                        == player && board.getPiece(row, column + 3)
                        == player)
                    return true;
            }
        }


        for (int row = 0; row < HEIGHT - 3; row++) {
            for (int column = 0; column < WIDTH; column++) {
                if (board.getPiece(row, column)
                        == player && board.getPiece(row + 1, column)
                        == player && board.getPiece(row + 2, column)
                        == player && board.getPiece(row + 3, column)
                        == player)
                    return true;
            }
        }


        // diagonal matches
        for (int row = 3; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH - 3; column++) {
                if (board.getPiece(row, column)
                        == player && board.getPiece(row - 1, column + 1)
                        == player && board.getPiece(row - 2, column + 2)
                        == player && board.getPiece(row - 3, column + 3)
                        == player)
                    return true;
            }
        }

        for (int row = 0; row < HEIGHT - 3; row++) {
            for (int column = 0; column < WIDTH - 3; column++) {
                if (board.getPiece(row, column)
                        == player && board.getPiece(row + 1, column + 1)
                        == player && board.getPiece(row + 2, column + 2)
                        == player && board.getPiece(row + 3, column + 3)
                        == player)
                    return true;
            }
        }
        return false;
    }
}
