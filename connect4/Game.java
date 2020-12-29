package com.ai.project;

public class Game {

    private static final int NUMBER_OF_ROW = 6;
    private static final int NUMBER_OF_COLUMN = 7;


    // finding the winner
    public boolean winner(Board board,String playerColor){

        for (int row = 0; row < NUMBER_OF_ROW ; row++)
            for (int column = 0; column < NUMBER_OF_COLUMN - 3; column++) {
                if (board.getPiece(row, column) != null &&
                        board.getPiece(row , column + 1) != null &&
                        board.getPiece(row , column + 2) != null &&
                        board.getPiece(row , column + 3) != null )

                    if (board.getPiece(row, column).getColor().equals(playerColor) &&
                            board.getPiece(row, column + 1).getColor().equals(playerColor) &&
                            board.getPiece(row, column + 2).getColor().equals(playerColor) &&
                            board.getPiece(row, column + 3).getColor().equals(playerColor)
                    ) return true;
            }



        for (int row = 0; row < NUMBER_OF_ROW - 3 ; row++)
            for (int column = 0; column < NUMBER_OF_COLUMN; column++) {
                if (board.getPiece(row, column) != null &&
                        board.getPiece(row + 1, column) != null &&
                        board.getPiece(row + 2, column) != null &&
                        board.getPiece(row + 3, column) != null )

                    if (board.getPiece(row, column).getColor().equals(playerColor) &&
                            board.getPiece(row + 1, column).getColor().equals(playerColor) &&
                            board.getPiece(row + 2, column).getColor().equals(playerColor) &&
                            board.getPiece(row + 3, column).getColor().equals(playerColor)
                    )
                        return true;
            }


        // diagonal matches
        for (int row = 3; row < NUMBER_OF_ROW ; row++)
            for (int column = 0; column < NUMBER_OF_COLUMN - 3; column++) {
                if (board.getPiece(row, column) != null &&
                        board.getPiece(row - 1, column + 1) != null &&
                        board.getPiece(row - 2, column + 2) != null &&
                        board.getPiece(row - 3, column + 3) != null )

                    if (board.getPiece(row, column).getColor().equals(playerColor) &&
                            board.getPiece(row - 1, column + 1).getColor().equals(playerColor) &&
                            board.getPiece(row - 2, column + 2).getColor().equals(playerColor) &&
                            board.getPiece(row - 3, column + 3).getColor().equals(playerColor)
                    )
                        return true;
            }

        for (int row = 0; row < NUMBER_OF_ROW - 3; row++)
            for (int column = 0; column < NUMBER_OF_COLUMN - 3; column++) {
                if (board.getPiece(row, column) != null &&
                        board.getPiece(row + 1, column + 1) != null &&
                        board.getPiece(row + 2, column + 2) != null &&
                        board.getPiece(row + 3, column + 3) != null )

                    if (board.getPiece(row, column).getColor().equals(playerColor) &&
                            board.getPiece(row + 1, column + 1).getColor().equals(playerColor) &&
                            board.getPiece(row + 2, column + 2).getColor().equals(playerColor) &&
                            board.getPiece(row + 3, column + 3).getColor().equals(playerColor)
                    )
                        return true;
            }







        return false;
    }
}
