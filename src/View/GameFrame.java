package src.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame implements ActionListener {
    private JFrame myFrame;
    private JMenuBar myOptionBar;
    private JMenu myFile, myHelp;
    private JMenuItem mySaveGame, myLoadGame, myExitGame, myAbout, myGamePlayInstruction;

    public GameFrame() {
        initializeFrame();
        initializeOptionBar();
    }

    private void initializeFrame() {
        this.myFrame = new JFrame("Trivia Maze");
        myFrame.setSize(650, 500);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setVisible(true);
    }
    private void initializeOptionBar() {
        this.myOptionBar = new JMenuBar();

        // Setting option
        this.myFile = new JMenu("File");

        this.mySaveGame = new JMenuItem("Save Game");
        this.myLoadGame = new JMenuItem("Load Game");
        this.myExitGame = new JMenuItem("Exit");

        this.mySaveGame.addActionListener(this);
        this.mySaveGame.addActionListener(this);
        this.myExitGame.addActionListener(this);

        this.myFile.add(mySaveGame);
        this.myFile.add(myLoadGame);
        this.myFile.add(myExitGame);

        this.myOptionBar.add(myFile);


        // Help option
        this.myHelp = new JMenu("Help");

        this.myAbout = new JMenuItem("About");
        this.myGamePlayInstruction = new JMenuItem("Game Play Instruction");

        this.myAbout.addActionListener(this);
        this.myGamePlayInstruction.addActionListener(this);

        this.myHelp.add(myAbout);
        this.myHelp.add(myGamePlayInstruction);

        this.myOptionBar.add(myHelp);

        this.myFrame.setJMenuBar(myOptionBar);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myExitGame) {
            int option = JOptionPane.showConfirmDialog(myFrame, "Are you sure to exit?", "Exit Game",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(option == JOptionPane.OK_OPTION) {
                myFrame.dispose();
            }
        }
        if(e.getSource() == myAbout) {
            StringBuilder about = new StringBuilder();
            about.append("<html>");
            about.append("This game is design with a goal of entertainment and eduction.<br>");
            about.append("Developer Team: Huy Huynh, Drew Brown, Jafar.<br>");
            about.append("App version: 1.0<br>");
            about.append("</html>");

            JOptionPane.showMessageDialog(myFrame, about.toString(), "Trivia Maza Game", JOptionPane.PLAIN_MESSAGE);
        }
    }
 }
