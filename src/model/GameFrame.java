package model;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameFrame {

    GamePanel gp;
    JFrame GameFrame;


    public GameFrame() {
        GameFrame = new JFrame();
        //MainPanel mp = new MainPanel();
        gp = new GamePanel();
        //gp.setVisible(false);
        GameFrame.add(gp);
        //GameFrame.add(mp);
        GameFrame.setTitle("Snake Game");
        GameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameFrame.setResizable(false);
        GameFrame.pack(); //make sure window is tight as possible around components
        GameFrame.setLocationRelativeTo(null); //open in the middle
        GameFrame.setVisible(true);
        //gp.setVisible(true);
    }
}
