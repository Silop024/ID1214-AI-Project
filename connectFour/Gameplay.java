package lab2connectFour;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;


public class Gameplay extends JPanel
{
    //When booting up the game, don't want to jump right into the action
    private boolean play = false;
    //Keeps track which player's turn it is, yellow starts so red is false
    private int player = 1;
    //Keep track of what tiles have been played and by whom
    private int[][] discPlacement = new int[6][7];

    public Gameplay()
    {
        addMouseListener(new MouseAdapter()
        {
        	public void mouseClicked(MouseEvent me)
        	{
        		putDisc(getArea(me.getX()));  
        	}
        });
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paint(Graphics g)
    {
        //Background
        g.setColor(Color.blue);
        g.fillRect(1,1, 800, 692);
        
        //White space for where game is played
        g.setColor(Color.white);
        for(int i = 10; i < 600; i += 100)
        {
        	for(int j = 10; j < 700; j += 100)
        	{
        		g.fillOval(j, i, 90, 90);
        	}
        }    
    }
    
    //@Override
    public void paintComponent(Graphics g, int x, int y)
    {
    	super.paintComponent(g);
    	if(player == 1)
    		g.setColor(Color.red);
    	else
    		g.setColor(Color.yellow);
    }
    
    public void putDisc(int x)
    {
    	int y = 0;
    	for(int i = 0; i < 6; i++)
    	{
    		if(discPlacement[i][x] == 0)
    		{
    			y = i;
    			break;
    		}
    	}
    	paintComponent(null, x, y);
    }
    
    public int getArea(int x)
    {
    	if(x < 110)
    		return 0;
    	else if(x >= 110 && x < 210)
    		return 1;
    	else if(x >= 210 && x < 310)
    		return 2;
    	else if(x >= 310 && x < 410)
    		return 3;
    	else if(x >= 410 && x < 510)
    		return 4;
    	else if(x >= 510 && x < 610)
    		return 5;
    	else return 6;
    }


//    //@Override
//    public void mouseClicked(MouseEvent e)
//    {
//    	putDisc(getArea(e.getX()));    	
//    }
//
//    //@Override
//    public void mouseEntered(MouseEvent e)
//    {
//
//    }
//
//    //@Override
//    public void mouseExited(MouseEvent e)
//    {
//
//    }
//
//    //@Override
//    public void mousePressed(MouseEvent e)
//    {
//
//    }
//
//    //@Override
//    public void mouseReleased(MouseEvent e)
//    {
//
//    }
}
