package src.View;

import src.Model.Game.GameLogic;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameIntro implements ActionListener, ChangeListener {
    private JFrame myFrame;
    private JLabel myLabel;
    private ImageIcon myImage;
    private JButton myStartButton, mySettingButton, myQuitButton;
    private JPanel optionPanel;
    private Clip introSoundClip, clickSoundClip;
    private JSlider volumeSlider, soundFXSlider;
    private JComboBox<String> languageComboBox;

    private String[] languages = {"English", "Vietnamese", "French"};

    public GameIntro() {
        InitializeMainFrame();
        InitializeOptionPanel();
        InitializePicture();
        loadBackGroundMusic(); // Load the background music
        playBackgroundMusic(); // Start playing the background music
        loadClickSound();
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
        myImage = new ImageIcon("cars-maze/soundimage/Maze.jpeg");

        myLabel = new JLabel();
        myLabel.setIcon(myImage);
        myLabel.setBounds(0, 0, myFrame.getWidth(), myFrame.getHeight());

        myFrame.add(myLabel, BorderLayout.CENTER);
    }
    private void InitializeOptionPanel() {
        ImageIcon startIcon = new ImageIcon("cars-maze/soundimage/Start.png");
        ImageIcon settingIcon = new ImageIcon("cars-maze/soundimage/Setting.png");
        ImageIcon quitIcon = new ImageIcon("cars-maze/soundimage/Quit.png");


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
    public void initializeSetting() {
        JFrame settingFrame = new JFrame("Game Setting");
        settingFrame.setSize(400, 300);
        settingFrame.setResizable(false);
        settingFrame.setVisible(true);
        settingFrame.setLocationRelativeTo(null);
        settingFrame.setLayout(null);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setBounds(110,20, 200,30);
        volumeSlider.addChangeListener(this);

        soundFXSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        soundFXSlider.setBounds(110, 70, 200,30);

        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setBounds(110, 120, 150,30);
        languageComboBox.addActionListener(this);


        JLabel volumeLabel = new JLabel("Volume");
        volumeLabel.setBounds(20, 20, 70,20);
        JLabel soundFXLabel = new JLabel("Sound fx");
        soundFXLabel.setBounds(20, 70, 70, 20);
        JLabel languageLabel = new JLabel("Language");
        languageLabel.setBounds(20, 120, 70, 20);

        settingFrame.add(soundFXLabel);
        settingFrame.add(soundFXSlider);
        settingFrame.add(volumeLabel);
        settingFrame.add(volumeSlider);
        settingFrame.add(languageLabel);
        settingFrame.add(languageComboBox);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myStartButton) {
            playClickSound();


            GameLogic gameLogic = new GameLogic();

            String username = JOptionPane.showInputDialog(null, "Enter your name:", "Username", JOptionPane.QUESTION_MESSAGE);

            if(username != null) {
                gameLogic.setUserName(username);
                myFrame.dispose(); // dispose the intro
                GameFrame gameFrame = new GameFrame(gameLogic); // open the new game frame.
            } else {
                JOptionPane.showMessageDialog(null, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }


        }
        if(e.getSource() == myQuitButton) {
            playClickSound();
            int option = JOptionPane.showConfirmDialog(myFrame, "Are you sure to exit?", "Exit Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(option == JOptionPane.OK_OPTION) {
                myFrame.dispose();
            }

        }
        if(e.getSource() == mySettingButton) {
            playClickSound();
            initializeSetting();
        }
        if(e.getSource() == languageComboBox) {
            String selectedLanguage = (String) languageComboBox.getSelectedItem();
            switchLanguage(selectedLanguage);
        }
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == volumeSlider) {
            setVolume(volumeSlider.getValue()); // Adjust volume based on slider value
        }
    }
    /**
     * Feature coming Soon!
     */
    public void switchLanguage(String language) {
        System.out.println("Selected language: " + language);
    }


    public void setVolume(int volume) {
        if (introSoundClip != null) {
            FloatControl volumeControl = (FloatControl) introSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = volumeControl.getMaximum() - volumeControl.getMinimum();
            float gain = (range * volume / 100) + volumeControl.getMinimum();
            volumeControl.setValue(gain);
        }
    }

    // Adding click sound for button.
    public void loadClickSound() {
        try {
            File soundFile = new File("cars-maze/soundimage/ClickSound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clickSoundClip = AudioSystem.getClip();
            clickSoundClip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playClickSound() {
        if(clickSoundClip != null) {
            clickSoundClip.setFramePosition(0);
            clickSoundClip.start();
        }
    }

    // Adding Intro Music.
    public void loadBackGroundMusic() {
        try {
            File soundFile = new File("cars-maze/soundimage/IntroMusic.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            introSoundClip = AudioSystem.getClip();
            introSoundClip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playBackgroundMusic() {
        if (introSoundClip != null) {
            setVolume(50);
            introSoundClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
        }
    }
}
