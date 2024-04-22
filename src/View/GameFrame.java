package src.View;

import javax.swing.*;

public class GameFrame {
    private final JFrame frame;


    public GameFrame() {
        this.frame = new JFrame("Trivia Maze");
        frame.setSize(650, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
