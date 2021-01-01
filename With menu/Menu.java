package com.ai.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    JFrame frame;
    JPanel panel;

    public Menu(){
        frame = new JFrame("Connect4 - BreadComrade, Inc");
        frame.setSize(750,500);  // size of the frame
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // frame will open in the middle of screen

        ImageIcon backGround = new ImageIcon("C:\\Users\\You Bastard!\\Desktop\\image\\bg.jpg");
        panel = new JPanel();
        JLabel label = new JLabel(backGround);
        panel.add(label);
        frame.add(panel);


    }

    public int startMenu(){

      int[] condition = {0};
        JButton singlePlayer = new JButton("Single player");
        singlePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new GUI();
                    }
                });
            }
        });
        JButton multiPlayer = new JButton("Multiplayer");
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
        panel.add(singlePlayer);
        panel.add(multiPlayer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println(condition[0]);
        return condition[0];
    }

    public static void main(String[] args) {
        Menu m = new Menu();
        m.startMenu();
    }
}
