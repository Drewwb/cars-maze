package src.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameIntro {
    private JFrame myFrame;
    private JLabel myLabel;
    private ImageIcon myImage;
    private JButton myStartButton, mySettingButton, myQuitButton;
    private JPanel optionPanel;

    public GameIntro() {
        InitializeMainFrame();
        InitializeOptionPanel();
        InitializePicture();
    }
    private void InitializeMainFrame() {
        myFrame = new JFrame("Trivia Maze");
        myFrame.setSize(736, 736);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);

        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout(null);
    }
    private void InitializePicture() {
        myImage = new ImageIcon("Maze.jpeg");

        myLabel = new JLabel();
        myLabel.setIcon(myImage);
        myLabel.setBounds(0, 0, myFrame.getWidth(), myFrame.getHeight());

        myFrame.add(myLabel, BorderLayout.CENTER);
    }
    private void InitializeOptionPanel() {
        ImageIcon startIcon = new ImageIcon("Start.png");
        ImageIcon settingIcon = new ImageIcon("Setting.png");
        ImageIcon quitIcon = new ImageIcon("Quit.png");



        optionPanel = new JPanel();
        optionPanel.setLayout(null);
        optionPanel.setBackground(new Color(0, 255, 0, 100));
        optionPanel.setBorder(new LineBorder(Color.BLACK, 3));
        optionPanel.setBounds(280, 235, 185, 200);

        // Start Game Button
        myStartButton = new JButton();
        myStartButton.setBounds(28, 40, 135, 34);
        myStartButton.setIcon(startIcon);
        myStartButton.setFocusable(false);

        // Setting Game Button
        mySettingButton = new JButton();
        mySettingButton.setBounds(28, 90, 135, 34);
        mySettingButton.setIcon(settingIcon);
        mySettingButton.setFocusable(false);

        // Quit Game Button
        myQuitButton = new JButton();
        myQuitButton.setBounds(28, 140, 135, 34);
        myQuitButton.setIcon(quitIcon);
        myQuitButton.setFocusable(false);


        optionPanel.add(myQuitButton);
        optionPanel.add(mySettingButton);
        optionPanel.add(myStartButton);



        myFrame.add(optionPanel);
    }
}
