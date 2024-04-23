package src.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame implements ActionListener {
    private JFrame myFrame;
    private JMenuBar myOptionBar;
    private JMenu mySetting, myHelp;
    private JMenuItem myStart, myReset, myExit;

    public GameFrame() {
        initializeFrame();
        initializeOptionBar();
    }

    private void initializeFrame() {
        this.myFrame = new JFrame();
        myFrame.setSize(650, 500);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setVisible(true);
    }
    private void initializeOptionBar() {
        this.myOptionBar = new JMenuBar();

        // Setting option
        this.mySetting = new JMenu("Setting");

        this.myStart = new JMenuItem("Start");
        this.myReset = new JMenuItem("Rest");
        this.myExit = new JMenuItem("Exit");

        myExit.addActionListener(this);
        myReset.addActionListener(this);
        myStart.addActionListener(this);

        this.mySetting.add(myStart);
        this.mySetting.add(myReset);
        this.mySetting.add(myExit);


        this.myOptionBar.add(mySetting);

        this.myFrame.setJMenuBar(myOptionBar);
        // Help option

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myExit) {
            myFrame.dispose();
        }
    }
 }
