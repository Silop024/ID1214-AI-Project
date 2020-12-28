package com.ai.project;

public class Board {
    private static final int NUMBER_OF_ROW = 6;
    private static final int NUMBER_OF_COLUMN = 7;

    public Piece[][] board = new Piece[NUMBER_OF_ROW][NUMBER_OF_COLUMN];



    // helper class
    public  class Piece {
        private final String color;

        public Piece(String color){
            this.color = color;
        }

        public String getColor(){
            return color;
        }

    }

    // constructor
    public Board(){
        for (int i = 0; i < NUMBER_OF_ROW;i++ )
            for (int j =0; j< NUMBER_OF_COLUMN; j++)
                board[i][j] = null;
    }



    // adding one piece to the board
    public void addPiece(int column, String color) {
        int row = NUMBER_OF_ROW - 1;

        for (int i = NUMBER_OF_ROW - 1; i > 0; i--) {
            if (hasPiece(i, column))
                row--;



            if (hasPiece(0, column)) {
                System.out.println("column is full");
                return;
            }
        }

        board[(row)][column] = new Piece(color);
    }


    // getting a piece from the board
    public Piece getPiece(int row,int column)  {

        return board[row][column];
    }


    public String  toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_ROW;i++ ) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                sb.append("|");
                if (board[i][j] == null)
                    sb.append("_");
                else
                    sb.append(board[i][j].getColor());
                sb.append("|");
            }
            sb.append('\n');
        }

    return String.valueOf(sb);
    }


    public boolean hasPiece(int row,int column){
        return board[row][column] !=null;
    }



}
