package src.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame implements ActionListener {
    private JFrame myFrame;
    private JMenuBar myOptionBar;
    private JMenu myFile, myHelp;
    private JMenuItem mySaveGame, myLoadGame, myExitGame, myAbout, myGamePlayInstruction;
    private JPanel myUserPanel, mazePanel, controlPanel, spellPanel, questionPanel;
    private JLabel backGroundLabel;
    private ImageIcon myBackGroundIcon;
    private JRadioButton option1, option2, option3, option4;
    private JButton submitButton;

    public GameFrame() { //test for huy pull
        initializeFrame();
        initializeUserPanel(); // need to come first before set background
        initializeMazePanel();
        initializeControlPanel();
        initializeSpellPanel();
        initializeMCQuestionPanel();

        initializeBackGround();
        initializeOptionBar();

    }

    private void initializeMCQuestionPanel() {
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(385, 450, 335, 225);
        questionPanel.setBackground(new Color(240, 240, 240));

        JLabel questionLabel = new JLabel("What is the capital of France?");
        questionLabel.setBounds(10, 10, 300, 30);

        option1 = new JRadioButton("Paris");
        option2 = new JRadioButton("London");
        option3 = new JRadioButton("Berlin");
        option4 = new JRadioButton("Madrid");

        option1.setBounds(10, 50, 150, 30); // Set the bounds for option1
        option2.setBounds(10, 90, 150, 30); // Set the bounds for option2
        option3.setBounds(10, 130, 150, 30); // Set the bounds for option3
        option4.setBounds(10, 170, 150, 30); // Set the bounds for option4

        option1.setOpaque(false); // Set background color of radio buttons to transparent
        option2.setOpaque(false);
        option3.setOpaque(false);
        option4.setOpaque(false);

        addDoubleClickListener(option1);
        addDoubleClickListener(option2);
        addDoubleClickListener(option3);
        addDoubleClickListener(option4);

        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 170, 100, 30); // Set the bounds for the submit button
        submitButton.addActionListener(this);


        questionPanel.add(questionLabel);
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(option4);
        questionPanel.add(submitButton);

        myFrame.add(questionPanel);
    }

    private void initializeSAQQuestionPanel() { //add action listener for user input
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(385, 450, 335, 225);
        questionPanel.setBackground(new Color(240, 240, 240));

        JLabel questionLabel = new JLabel("What is the capital of France?");
        questionLabel.setBounds(10, 10, 300, 30);

        JTextField textField = new JTextField(20);

        textField.setBounds(10, 50, 150, 30); // Set the bounds for option1

        textField.setOpaque(false); // Set background color of radio buttons to transparent

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 170, 100, 30); // Set the bounds for the submit button
        submitButton.addActionListener(this);

        questionPanel.add(questionLabel);
        questionPanel.add(textField);
        questionPanel.add(submitButton);

        myFrame.add(questionPanel);
    }

    private void initializeTFQuestionPanel() {
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(385, 450, 335, 225);
        questionPanel.setBackground(new Color(240, 240, 240));

        JLabel questionLabel = new JLabel("True or False?");
        questionLabel.setBounds(10, 10, 300, 30);

        option1 = new JRadioButton("True");
        option2 = new JRadioButton("False");

        option1.setBounds(10, 50, 150, 30); // Set the bounds for option1
        option2.setBounds(10, 90, 150, 30); // Set the bounds for option2

        option1.setOpaque(false); // Set background color of radio buttons to transparent
        option2.setOpaque(false);

        addDoubleClickListener(option1);
        addDoubleClickListener(option2);

        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 170, 100, 30); // Set the bounds for the submit button
        submitButton.addActionListener(this);


        questionPanel.add(questionLabel);
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(submitButton);

        myFrame.add(questionPanel);
    }



    private void addDoubleClickListener(JRadioButton radioButton) {
        radioButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    radioButton.setSelected(false);
                }
            }
        });
    }



    private void initializeSpellPanel() {
        spellPanel = new JPanel();
        spellPanel.setLayout(null);
        spellPanel.setBorder(new LineBorder(Color.darkGray, 3));
        spellPanel.setBounds(10, 590, 350,85);
        spellPanel.setBackground(new Color(0, 0, 0, 100));

        myFrame.add(spellPanel);

    }

    private void initializeControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBorder(new LineBorder(Color.darkGray, 3));
        controlPanel.setBounds(10, 450, 350,120);
        controlPanel.setBackground(new Color(0, 0, 0, 100));

        ImageIcon up = new ImageIcon("soundimage/up.png");
        JButton moveUp = new JButton();
        moveUp.setIcon(up);
        moveUp.setBounds(40, 15, 30,30);

        ImageIcon left = new ImageIcon("soundimage/left.png");
        JButton moveLeft = new JButton();
        moveLeft.setIcon(left);
        moveLeft.setBounds(5, 50, 30,30);

        ImageIcon right = new ImageIcon("soundimage/right.png");
        JButton moveRight = new JButton();
        moveRight.setIcon(right);
        moveRight.setBounds(75, 50, 30,30);

        ImageIcon down = new ImageIcon("soundimage/down.png");
        JButton moveDown = new JButton();
        moveDown.setIcon(down);
        moveDown.setBounds(40, 85, 30,30);


        controlPanel.add(moveUp);
        controlPanel.add(moveLeft);
        controlPanel.add(moveRight);
        controlPanel.add(moveDown);

        myFrame.add(controlPanel);
    }

    private void drawBrickWall(Graphics g, int x, int y, int size) {
        int brickWidth = size / 5;
        int brickHeight = size / 4;
        Color brickColor1 = new Color(192, 128, 64);
        Color brickColor2 = new Color(160, 96, 48);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(brickColor1);
                } else {
                    g.setColor(brickColor2);
                }
                g.fillRect(x + i * brickWidth, y + j * brickHeight, brickWidth, brickHeight);
            }
        }
    }

    private void drawDoors(Graphics g, int x, int y, int size) {
        int doorWidth = size / 13;
        int doorHeight = size / 4;
        Color doorColor1 = new Color(5, 4, 4);
        g.setColor(doorColor1);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(doorColor1);
                } else {
                    g.setColor(doorColor1);
                }
                g.fillRect(x + i * doorWidth, y + j * doorHeight, doorWidth, doorHeight);
            }
        }

    }

    private void drawDoors2(Graphics g, int x, int y, int size) {
        int doorWidth = size / 5;
        int doorHeight = size / 13;
        Color doorColor1 = new Color(5, 4, 4);
        g.setColor(doorColor1);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(doorColor1);
                } else {
                    g.setColor(doorColor1);
                }
                g.fillRect(x + i * doorWidth, y + j * doorHeight, doorWidth, doorHeight);
            }
        }

    }

    private void initializeMazePanel() {
        mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int numRows = 9; // Number of rows in the maze
                int numCols = 9; // Number of columns in the maze
                int panelWidth = getWidth(); // Width of the panel
                int panelHeight = getHeight(); // Height of the panel

                int cellSize = Math.min(panelWidth / numCols, panelHeight / numRows); // Calculate cell size to fit the maze in the panel

                int mazeWidth = numCols * cellSize; // Actual width of the maze
                int mazeHeight = numRows * cellSize; // Actual height of the maze
                int xOffset = (panelWidth - mazeWidth) / 2; // Offset to center the maze horizontally
                int yOffset = (panelHeight - mazeHeight) / 2; // Offset to center the maze vertically

                int[][] maze = {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 2, 0, 2, 0, 2, 0, 1},
                        {1, 3, 1, 3, 1, 3, 1, 3, 1},
                        {1, 0, 2, 0, 2, 0, 2, 0, 1},
                        {1, 3, 1, 3, 1, 3, 1, 3, 1},
                        {1, 0, 2, 0, 2, 0, 2, 0, 1},
                        {1, 3, 1, 3, 1, 3, 1, 3, 1},
                        {1, 0, 2, 0, 2, 0, 2, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1}
                };
                for (int i = 0; i < maze.length; i++) {
                    for (int j = 0; j < maze[i].length; j++) {
                        if (maze[i][j] == 1) {
                            drawBrickWall(g, xOffset + j * cellSize, yOffset + i * cellSize, cellSize);
                        } else if (maze[i][j] == 2) {
                            drawDoors(g, xOffset + j * cellSize, yOffset + i * cellSize, cellSize);
                        } else if (maze[i][j] == 3) {
                            drawDoors2(g, xOffset + j * cellSize, yOffset + i * cellSize, cellSize);
                        }
                    }
                }
            }
        };
        mazePanel.setLayout(null);
        mazePanel.setBorder(new LineBorder(Color.darkGray, 3));
        mazePanel.setBounds(10, 80, 350, 350); // Adjusted the size to fit the maze
        mazePanel.setBackground(new Color(0, 0, 0, 100));

        myFrame.add(mazePanel);
    }



    private void initializeUserPanel() {
        myUserPanel = new JPanel();
        myUserPanel.setLayout(null);
        myUserPanel.setBorder(new LineBorder(Color.darkGray, 3));
        myUserPanel.setBounds(10, 10, 715,50);
        myUserPanel.setBackground(new Color(0, 0, 0, 100));

        JLabel userNameLabel = new JLabel("USER NAME: # ");
        userNameLabel.setBounds(40,8,100,40);

        JLabel userWinSteaksLabel = new JLabel("WIN STREAKS: ");
        userWinSteaksLabel.setBounds(235, 8, 100,40);

        JLabel userSpellLabel = new JLabel("SPELL: ");
        userSpellLabel.setBounds(400, 8, 100,40);

        JLabel userSkipDoorSpell = new JLabel("skip door: # ");
        userSkipDoorSpell.setBounds(500, 8, 100,40);

        JLabel userHintSpell = new JLabel("hint : # ");
        userHintSpell.setBounds(640, 8, 100,40);


        myUserPanel.add(userSpellLabel);
        myUserPanel.add(userSkipDoorSpell);
        myUserPanel.add(userHintSpell);
        myUserPanel.add(userWinSteaksLabel);
        myUserPanel.add(userNameLabel);



        myFrame.add(myUserPanel);
    }

    private void initializeFrame() {
        this.myFrame = new JFrame("Trivia Maze");
        myFrame.setSize(736, 736);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        myFrame.setResizable(false);
        myFrame.setLayout(null);
        myFrame.setVisible(true);
    }

    private void initializeBackGround() {
        myBackGroundIcon = new ImageIcon("soundimage/Background2.jpeg");
        backGroundLabel = new JLabel(myBackGroundIcon);
        backGroundLabel.setBounds(0, 0, myFrame.getWidth(), myFrame.getHeight());
        myFrame.add(backGroundLabel);
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

            JOptionPane.showMessageDialog(myFrame, about.toString(), "Trivia Maze Game", JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource() == submitButton) {
            String correctAnswer = "Paris";
            String userAnswer = "";
            boolean isCorrect = false;

            if (option1.isSelected()) {
                userAnswer = option1.getText();
                if(correctAnswer.equals(userAnswer)) {
                    isCorrect = true;
                }
            } else if (option2.isSelected()) {
                userAnswer = option2.getText();
                if(correctAnswer.equals(userAnswer)) {
                    isCorrect = true;
                }
            } else if (option3.isSelected()) {
                userAnswer = option3.getText();
                if(correctAnswer.equals(userAnswer)) {
                    isCorrect = true;
                }
            } else if (option4.isSelected()) {
                userAnswer = option4.getText();
                if(correctAnswer.equals(userAnswer)) {
                    isCorrect = true;
                }
            }

            if(isCorrect) {
                JOptionPane.showMessageDialog(null, "Congrats! Correct Answer");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Answer!");
            }

        }
    }
 }
