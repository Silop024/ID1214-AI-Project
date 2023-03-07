package connectfour;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

abstract class Game
{
    protected final JFrame frame;
    protected final JPanel panel;
    protected final Board board;

    protected int playedMoves = 0;

    public Game()
    {
        // Init board
        board = new Board();

        // Init frame
        frame = new JFrame("Connect4 - BreadComrade, Inc"); // title of the game
        frame.setSize(750, 650);  // size of the frame
        frame.setLocationRelativeTo(null);

        // Init panel
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        ImageIcon iconEmpty = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/empty.png")));

        int ROWS = 6;
        int COLS = 7;
        for (int row = 0; row < ROWS; row++)
            for (int column = 0; column < COLS; column++) {
                JButton jb = new JButton();
                jb.setIcon(iconEmpty);
                jb.setPreferredSize(new Dimension(100, 100));
                jb.setName(Integer.toString(column));

                jb.addActionListener(e -> updater(((JButton) e.getSource())));
                panel.add(jb);
            }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

    public abstract void updater(JButton button);
}
