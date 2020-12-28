package com.ai.project;

public class Game {

    private static final int NUMBER_OF_ROW = 6;
    private static final int NUMBER_OF_COLUMN = 7;


    // finding the winner
    public boolean winner(Board board,String playerColor){
        boolean win = false;
        int winStreak = 0;

        // vertical matches
        for (int row = 0; row < NUMBER_OF_ROW ; row++) {
            winStreak = 0;
            for (int column = 0; column < NUMBER_OF_COLUMN; column++) {
                if (board.getPiece(row, column) != null)
                    if (board.getPiece(row, column).getColor().equals(playerColor))
                        winStreak++;

                    else {
                        if (winStreak == 4)
                            break;
                        else
                            winStreak = 0;
                    }
            }
            if (winStreak == 4)
                break;
        }

        if (winStreak == 4)
            win = true;


        // horizontal matches
        for (int column = 0; column < NUMBER_OF_COLUMN; column++) {
            winStreak = 0;
            for (int row = 0; row < NUMBER_OF_ROW; row++) {
                if (board.getPiece(row, column) != null)
                    if (board.getPiece(row, column).getColor().equals(playerColor))
                        winStreak++;
                    else {
                        if (winStreak == 4)
                            break;

                        else
                            winStreak = 0;
                    }
            }
            if (winStreak == 4)
                break;
        }
        if (winStreak == 4)
            win = true;




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
                    ) {
                        win = true;
                        break;
                    }
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
                    ) {
                        win = true;
                        break;
                    }
            }







        return win;
    }
}
