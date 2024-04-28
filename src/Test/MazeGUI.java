package src.Test;

import javax.swing.*;
import java.awt.*;

public class MazeGUI extends JFrame {
    private JPanel mazePanel;
    private final int mazeSize = 4; // Size of the maze (4x4)

    public MazeGUI() {
        setTitle("Maze GUI");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mazePanel = new JPanel(new GridLayout(mazeSize, mazeSize)); // Create a grid layout for the maze
        initializeMaze();

        add(mazePanel);
        setVisible(true);
    }

    private void initializeMaze() {
        for (int row = 0; row < mazeSize; row++) {
            for (int col = 0; col < mazeSize; col++) {
                JPanel cell = new JPanel();
                cell.setLayout(new BorderLayout()); // Use BorderLayout to add walls
                addWalls(cell, row, col);
                mazePanel.add(cell);
            }
        }
    }

    private void addWalls(JPanel cell, int row, int col) {
        // Add walls to the cell based on its position in the maze
        if (row == 0) cell.add(new JLabel("NORTH"), BorderLayout.NORTH);
        if (col == 0) cell.add(new JLabel("WEST"), BorderLayout.WEST);
        if (row == mazeSize - 1) cell.add(new JLabel("SOUTH"), BorderLayout.SOUTH);
        if (col == mazeSize - 1) cell.add(new JLabel("EAST"), BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeGUI());
    }
}

