package connectfour;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MultiPlayer extends Game
{
    // all the shit happens here
    @Override
    public void updater(JButton button)
    {
        ImageIcon iconRed = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/red.png")));
        ImageIcon iconYellow = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/yellow.png")));
        int theRow = 35;
        int row = 0;

        int column = Integer.parseInt(button.getName());

        for (int i = 0; i < 6; i++) {
            if (row == 5) break;

            if (board.hasPiece(column, i)) {
                theRow = theRow - 7;
                row++;
            }
        }

        if (counter % 2 == 0 && !board.hasPiece(column, row)) {
            JButton bt = (JButton) panel.getComponent(theRow + column);
            bt.setIcon(iconRed);
            board.addPiece(column);
            System.out.println(board);
            counter++;
        } else if (!board.hasPiece(column, row)) {
            JButton bt = (JButton) panel.getComponent(theRow + column);
            bt.setIcon(iconYellow);
            board.addPiece(column);
            System.out.println(board);
            counter++;
        }

        if (board.winner(board, 1)) {

            JOptionPane.showMessageDialog(null, "Red is the winner");
            frame.dispose();
        }

        if (board.winner(board, 2)) {
            JOptionPane.showMessageDialog(null, "Yellow is the winner");
            frame.dispose();
        }
    }
}
