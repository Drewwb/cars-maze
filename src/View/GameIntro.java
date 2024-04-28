package src.View;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameIntro implements ActionListener {
    private JFrame myFrame;
    private JLabel myLabel;
    private ImageIcon myImage;
    private JButton myStartButton, mySettingButton, myQuitButton;
    private JPanel optionPanel;
    private Clip introSoundClip;

    public GameIntro() {
        InitializeMainFrame();
        InitializeOptionPanel();
        InitializePicture();
        loadBackGroundMusic(); // Load the background music
        playBackgroundMusic(); // Start playing the background music
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
        myStartButton.addActionListener(this);

        // Setting Game Button
        mySettingButton = new JButton();
        mySettingButton.setBounds(28, 90, 135, 34);
        mySettingButton.setIcon(settingIcon);
        mySettingButton.setFocusable(false);
        mySettingButton.addActionListener(this);

        // Quit Game Button
        myQuitButton = new JButton();
        myQuitButton.setBounds(28, 140, 135, 34);
        myQuitButton.setIcon(quitIcon);
        myQuitButton.setFocusable(false);
        myQuitButton.addActionListener(this);

        // Adding into panel
        optionPanel.add(myQuitButton);
        optionPanel.add(mySettingButton);
        optionPanel.add(myStartButton);

        // Adding to frame
        myFrame.add(optionPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myStartButton) {
            myFrame.dispose();
            GameFrame gameFrame = new GameFrame();
        }
        if(e.getSource() == myQuitButton) {
            myFrame.dispose();
        }
    }


    // Adding Intro Music.
    public void loadBackGroundMusic() {
        try {
            File soundFile = new File("IntroMusic.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            introSoundClip = AudioSystem.getClip();
            introSoundClip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playBackgroundMusic() {
        if (introSoundClip != null) {
            introSoundClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
        }
    }
}
