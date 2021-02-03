package connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinglePlayer
{
    private final JFrame frame;
    private final JPanel panel;

    private Board board;
    private Board board2;

    public SinglePlayer()
    {
        board = new Board();

        ImageIcon iconEmpty = new ImageIcon(getClass().getResource("/resources/empty.png"));

        frame = new JFrame("connect 4 - BreadComrade, Inc");
        frame.setSize(750, 650);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for(int row = 0; row < 6; row++)
            for(int column = 0; column < 7; column++)
            {
                JButton jb = new JButton();
                jb.setIcon(iconEmpty);
                jb.setPreferredSize(new Dimension(100, 100));
                jb.setName(Integer.toString(column));

                jb.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        updater(((JButton) e.getSource()));
                    }
                });
                panel.add(jb);
            }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void updater(JButton button)
    {
    	ImageIcon iconRed = new ImageIcon(getClass().getResource("/resources/red.png"));
        ImageIcon iconYellow = new ImageIcon(getClass().getResource("/resources/yellow.png"));
           
        int row = 0;
        int theRow = 35;
        board2 = new Board(board);

        if(1 + (board.getMoves() % 2) == 1)
        {
            int column = Integer.parseInt(button.getName());

            if(board.isPlayable(column))
            {
            	for(int i = 0; i < 6; i++) {
                    if (row == 5)
                        break;
                    if(board.getBoard()[column][i] != 0) {
                        theRow = theRow - 7;
                        row++;
                    }
                }
                JButton jb = (JButton) panel.getComponent(column + theRow);
                jb.setIcon(iconRed);
                if(board.isWinningMove(column))
                {
                    JOptionPane.showMessageDialog(null, "Red player has won");
                    frame.dispose();
                }
                board.addPiece(column);
            }
        }
        board2 = new Board(board);
        if (1 + (board.getMoves() % 2) == 2)
        {
            row = 0;
            theRow = 35;
        	System.out.println("AI playing");
            Solver s = new Solver();
            int aiMove = s.solve(board2);

            try{
            	if(board.isPlayable(aiMove))
                {
                	for(int i = 0; i < 6; i++)
                	{
                        if (row == 5)
                            break;
                        if(board.getBoard()[aiMove][i] != 0)
                        {
                            theRow = theRow - 7;
                            row++;
                        }
                    }
                	JButton jb = (JButton) panel.getComponent(aiMove + theRow);
                	jb.setIcon(iconYellow);
                	if(board.isWinningMove(aiMove))
                	{
                    	JOptionPane.showMessageDialog(null, "Yellow AI has won");
                    	frame.dispose();
                	}
                	board.addPiece(aiMove);
                }
            } catch (Exception e) {
                System.out.println("Bad AI");
            }
        }
        System.out.println(board);
    }
}
