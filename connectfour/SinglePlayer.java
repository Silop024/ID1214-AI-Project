package connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SinglePlayer extends Game
{
    public void updater(JButton button)
    {
        ImageIcon iconRed = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/red.png")));
        ImageIcon iconYellow = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/yellow.png")));

        int row = 0;
        int theRow = 35;

        if (1 + (board.getMoves() % 2) == 1) {
            int column = Integer.parseInt(button.getName());

            if (board.isPlayable(column)) {
                findRow(iconRed, row, theRow, column);
                if (board.isWinningMove(column)) {
                    JOptionPane.showMessageDialog(null, "Red player has won");
                    frame.dispose();
                }
                board.addPiece(column);
            }
        } else if (1 + (board.getMoves() % 2) == 2) {
            Board board2 = new Board(board);
            System.out.println("AI playing");
            Solver s = new Solver();
            int aiMove = s.solve(board2);

            if (board.isPlayable(aiMove)) {
                findRow(iconYellow, row, theRow, aiMove);
                if (board.isWinningMove(aiMove)) {
                    JOptionPane.showMessageDialog(null, "Yellow AI has won");
                    frame.dispose();
                }
                board.addPiece(aiMove);
            }
        }
        System.out.println(board);
    }

    private void findRow(ImageIcon iconRed, int row, int theRow, int column)
    {
        for (int i = 0; i < 6; i++) {
            if (row == 5) break;
            if (board.getBoard()[column][i] != 0) {
                theRow = theRow - 7;
                row++;
            }
        }
        JButton jb = (JButton) panel.getComponent(column + theRow);
        jb.setIcon(iconRed);
    }
}
