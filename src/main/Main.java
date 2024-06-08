package main;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Tresure Founding");

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack(); // force the size to be the preferred size

        frame.setLocationRelativeTo(null); // open the frame at the center of the screen
        frame.setVisible(true);

        gamePanel.startGameClock();

    } // main(.)
} // main.Main