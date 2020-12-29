package com.ai.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final JFrame frame;
    private final JPanel panel;

    private final Board board;
    private final int rows = 6;
    private int counter = 0; // is used for player's turn

    private int[] columnMoves = {0,0,0,0,0,0,0};


    // Constructor, making an empty grid
    public GUI() {
        board = new Board();
        ImageIcon iconEmpty = new ImageIcon("C:\\Users\\You Bastard!\\Desktop\\image\\empty.png");

        frame = new JFrame("Connect4 - BreadComrade, Inc"); // title of the game
        frame.setSize(750,650);  // size of the frame
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // frame will open in the middle of screen

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        

        //initializing the grid
        for(int row = 0; row < rows; row++)
            for (int col = 0; col < 7; col++) {
                JButton button = new JButton();
                button.setIcon(iconEmpty);
                button.setPreferredSize(new Dimension(100, 100)); // the size of every button

                // give the button a numeric value as name that represent the column of that button
                button.setName(Integer.toString(col));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updater(((JButton)e.getSource()));
                    }

                });
                panel.add(button);

            }


        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // all the shit happens here
    public void updater(JButton button) {
        ImageIcon iconRed = new ImageIcon("C:\\Users\\You Bastard!\\Desktop\\image\\red.png");
        ImageIcon iconYellow = new ImageIcon("C:\\Users\\You Bastard!\\Desktop\\image\\yellow.png");
        int theRow = 35;
        int row =  rows - 1;


        int column = Integer.parseInt(button.getName());

        for (int i = 5; i >=0 ; i--) {
            if (row == 0)
                break;
            if (board.hasPiece(row,column)) {
                theRow = theRow - 7;
                row--;
            }
        }


        if (counter % 2 ==0 && !board.hasPiece(row,column)) {
            JButton bt =(JButton)panel.getComponent(theRow + column);
            bt.setIcon(iconRed);
            board.addPiece(column,1);
            System.out.println(board);
            counter++;
            System.out.println("c moves " + columnMoves[column]);
            columnMoves[column]++;
        }

        // checking for the winner
        Game game = new Game();
        if (game.winner(board,2)){

            JOptionPane.showMessageDialog(null, "Player1 has won");
        }


        Position position = new Position(board);
        for (int i = 0 ; i < columnMoves.length;i++)
        System.out.print(columnMoves[i]);
        Solver solver = new Solver(position);
        int AIplay = solver.solve(position);
        System.out.println("bitch "+ AIplay);
        System.out.println(position);

        theRow = 35;
        row = 5;
        for (int i = 5; i >=0 ; i--) {
            if (row == 0)
                break;
            if (board.hasPiece(row, solver.getMove())) {
                theRow = theRow - 7;
                row--;
            }
        }
        if (counter % 2 == 1 &&!board.hasPiece(row,AIplay)) {

                JButton bt =(JButton)panel.getComponent(theRow + AIplay);
                bt.setIcon(iconYellow);
                board.addPiece(AIplay, 2);
                System.out.println(board);
                counter++;
                System.out.println("c moves " + columnMoves[AIplay]);
                columnMoves[AIplay]++;
            }


        // checking for the winner
        if (game.winner(board,2)){

            JOptionPane.showMessageDialog(null, "Player2 has won");
         }




    }

}