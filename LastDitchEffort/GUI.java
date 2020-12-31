package connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    private final JFrame frame;
    private final JPanel panel;

    private Board board;
    private Board board2;

    public GUI()
    {
        board = new Board();
        //board2 = new Board();

        ImageIcon iconEmpty = new ImageIcon("C:\\Users\\chimp\\Desktop\\AIProject-v2\\ProjectAI\\src\\connect4\\images\\empty.png");

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
                //int c = Integer.parseInt(jb.getName());
                //System.out.println(c);

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
        ImageIcon iconRed = new ImageIcon("C:\\Users\\chimp\\Desktop\\AIProject-v2\\ProjectAI\\src\\connect4\\images\\red.png");
        ImageIcon iconYellow = new ImageIcon("C:\\Users\\chimp\\Desktop\\AIProject-v2\\ProjectAI\\src\\connect4\\images\\yellow.png");
           
        int row = 0;
        int theRow = 35;
        board2 = new Board(board);

        if(1 + (board.getMoves() % 2) == 1)
        {
        	System.out.println("Human playing");
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
                }
                board.addPiece(column);
            }
        }
        else
        {
        	System.out.println("AI playing");
            Solver s = new Solver();
            int aiMove = s.solve(board2);
            System.out.println("AI SCORE = " + s.evaluation);
            
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
                	}
                	board.addPiece(aiMove);
                }
            } catch (Exception e) {
                System.out.println("Stinky AI");
            }
        }
        System.out.println(board);
    }
}
