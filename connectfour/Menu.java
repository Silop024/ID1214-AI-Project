package connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    JFrame frame;
    JPanel panel;

    public Menu(){
        frame = new JFrame("Connect4  - ID1214 AI project");
        frame.setSize(750,500);  // size of the frame
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // frame will open in the middle of screen

        ImageIcon connect4Image = new ImageIcon(getClass().getResource("/resources/bg.jpg"));

        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);

        JLabel imageLabel = new JLabel(connect4Image);
        Dimension sizeImage = imageLabel.getPreferredSize();
        panel.add(imageLabel);
        imageLabel.setBounds(20,20,sizeImage.width,sizeImage.height);

        JLabel text = new JLabel("BreadBrothers ™ ");
        text.setFont(new Font("Arial", Font.ITALIC,13));
        text.setForeground(Color.white);
        Dimension size = text.getPreferredSize();

        panel.add(text);
        text.setBounds(600,425,size.width,size.height);

        frame.add(panel);


    }

    public void startMenu(){

      int[] condition = {0};
        JButton singlePlayer = new JButton("Single player");
        singlePlayer.setFocusPainted(false);
        singlePlayer.setBackground(new Color(204, 4, 4));
        singlePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new SinglePlayer();
                    }
                });
            }
        });
        JButton multiPlayer = new JButton("Multiplayer");
        multiPlayer.setBackground(Color.yellow.darker());
        multiPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new MultiPlayer();
                        }
                    });
                }
        });
        Dimension sizeButton = singlePlayer.getPreferredSize();
        panel.add(singlePlayer);
        singlePlayer.setBounds(510,220,sizeButton.width,sizeButton.height);
        panel.add(multiPlayer);
        multiPlayer.setBounds(620,220,sizeButton.width,sizeButton.height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
