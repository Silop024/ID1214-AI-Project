package connectfour;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiPlayer  {

    private final JFrame frame;
    private final JPanel panel;

    private final Board board;
;

    private final int rows = 6;
    private int counter = 0; // is used for player's turn


    // Constructor, making an empty grid
    public MultiPlayer() {
        board = new Board();

        ImageIcon iconEmpty = new ImageIcon(getClass().getResource("/resources/empty.png"));

        frame = new JFrame("Connect4 - BreadComrade, Inc"); // title of the game
        frame.setSize(750,650);  // size of the frame
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
                // add the button to our panel
                panel.add(button);
            }


        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // all the shit happens here
    public void updater(JButton button) {
        ImageIcon iconRed = new ImageIcon(getClass().getResource("/resources/red.png"));
        ImageIcon iconYellow = new ImageIcon(getClass().getResource("/resources/yellow.png"));
        int theRow = 35;
        int row =  0 ;

        int column = Integer.parseInt(button.getName());


        for (int i = 0; i < 6 ; i++) {
            if (row == 5)
                break;
            if (board.hasPiece(column, i)) {
                theRow = theRow - 7;
                row++;
            }
        }


        if (counter % 2 ==0 && !board.hasPiece(column,row)) {
            JButton bt =(JButton)panel.getComponent(theRow + column);
            bt.setIcon(iconRed);
            board.addPiece(column);
            System.out.println(board);
            counter++;
        }
        else
        if (!board.hasPiece(column,row)) {
            JButton bt =(JButton)panel.getComponent(theRow + column);
            bt.setIcon(iconYellow);
            board.addPiece(column);
            System.out.println(board);
            counter++;
        }

        if (board.winner(board,1)){

            JOptionPane.showMessageDialog(null, "Red is the winner");
            frame.dispose();
        }


        if (board.winner(board,2)){
            JOptionPane.showMessageDialog(null, "Yellow is the winner");
            frame.dispose();

        }

    }

}
