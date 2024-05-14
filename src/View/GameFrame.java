package src.View;

import src.Model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame implements ActionListener {
    private final String PATH = "cars-maze/";
    private JFrame myFrame;
    private JMenuBar myOptionBar;
    private JMenu myFile, myHelp;
    private JMenuItem mySaveGame, myLoadGame, myExitGame, myAbout, myGamePlayInstruction;
    private JPanel myUserPanel, mazePanel, controlPanel, spellPanel, questionPanel;
    private JLabel backGroundLabel;
    private ImageIcon myBackGroundIcon;
    private JRadioButton option1, option2, option3, option4;
    private JButton submitButton;

    public GameFrame() { //test for drew
        initializeFrame();
        initializeUserPanel(); // need to come first before set background
        initializeMazePanel();
        initializeControlPanel();
        initializeSpellPanel();
        initializeQuestionPanel();

        initializeBackGround();
        initializeOptionBar();

    }
    private void initializeQuestionPanel() {
        QuestionFactory question = new QuestionFactory();

        Question myQuestion = question.createQuestion();

        if(myQuestion instanceof MultipleChoiceQuestion) {
            initializeMCQuestionPanel(myQuestion);
        } else if(myQuestion instanceof TrueFalseQuestion) {
            initializeTFQuestionPanel(myQuestion);
        } else if( myQuestion instanceof ShortAnswerQuestion) {
            initializeSAQQuestionPanel(myQuestion);
        }
    }

    private void initializeMCQuestionPanel(Question question) {
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(360, 450, 365, 225);
        questionPanel.setBackground(new Color(240, 240, 240));

        // type cast question.
        MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;

        String theQuestion = mcQuestion.getQuestion();
        JLabel questionLabel = new JLabel("<html><div style='width: 300px;'>" + theQuestion + "</div></html>");
        questionLabel.setBounds(10, 10, 335, 30);

        String theOption1 = mcQuestion.getOptions()[0];
        String theOption2 = mcQuestion.getOptions()[1];
        String theOption3 = mcQuestion.getOptions()[2];
        String theOption4 = mcQuestion.getOptions()[3];

        option1 = new JRadioButton(theOption1);
        option2 = new JRadioButton(theOption2);
        option3 = new JRadioButton(theOption3);
        option4 = new JRadioButton(theOption4);

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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correctAnswer = mcQuestion.getAnswer();
                boolean isCorrect = false;

                if(option1.isSelected()) {
                    isCorrect = option1.getText().equals(correctAnswer);
                } else if (option2.isSelected()) {
                    isCorrect = option2.getText().equals(correctAnswer);
                } else if(option3.isSelected()) {
                    isCorrect = option3.getText().equals(correctAnswer);
                } else if(option4.isSelected()) {
                    isCorrect = option4.getText().equals(correctAnswer);
                }

                if (isCorrect) {
                    JOptionPane.showMessageDialog(null, "Congrats! Correct Answer");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Answer!");
                }
            }
        });


        questionPanel.add(questionLabel);
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(option4);
        questionPanel.add(submitButton);

        myFrame.add(questionPanel);
    }

    private void initializeSAQQuestionPanel(Question question) { //add action listener for user input
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(360, 450, 365, 225);
        questionPanel.setBackground(new Color(240, 240, 240));


        ShortAnswerQuestion sqQuestion = (ShortAnswerQuestion) question;
        String theQuestion = sqQuestion.getQuestion();

        JLabel questionLabel = new JLabel("<html><div style='width: 300px;'>" + theQuestion + "</div></html>");
        questionLabel.setBounds(10, 10, 335, 30);

        JTextField textField = new JTextField(20);

        textField.setBounds(10, 50, 150, 30); // Set the bounds for option1

        textField.setOpaque(false); // Set background color of radio buttons to transparent

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 170, 100, 30); // Set the bounds for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correctAnswer = textField.getText();
                boolean isCorrect = false;

                if(textField.getText().equals(correctAnswer)) {
                    isCorrect = true;
                }

                if (isCorrect) {
                    JOptionPane.showMessageDialog(null, "Congrats! Correct Answer");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Answer!");
                }
            }
        });

        questionPanel.add(questionLabel);
        questionPanel.add(textField);
        questionPanel.add(submitButton);

        myFrame.add(questionPanel);
    }

    private void initializeTFQuestionPanel(Question question) {
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(360, 450, 365, 225);
        questionPanel.setBackground(new Color(240, 240, 240));

        TrueFalseQuestion tfQuestion = (TrueFalseQuestion) question;
        String theQuestion = tfQuestion.getQuestion();

        JLabel questionLabel = new JLabel("<html><div style='width: 300px;'>" + theQuestion + "</div></html>");
        questionLabel.setBounds(10, 10, 335, 30);

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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correctAnswer = tfQuestion.getAnswer();
                boolean isCorrect = false;

                if(tfQuestion.getAnswer().equals(correctAnswer)) {
                    isCorrect = true;
                }

                if (isCorrect) {
                    JOptionPane.showMessageDialog(null, "Congrats! Correct Answer");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Answer!");
                }
            }
        });


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
        spellPanel.setBounds(10, 590, 340,85);
        spellPanel.setBackground(new Color(0, 0, 0, 100));

        myFrame.add(spellPanel);

    }

    private void initializeControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBorder(new LineBorder(Color.darkGray, 3));
        controlPanel.setBounds(10, 450, 340,120);
        controlPanel.setBackground(new Color(0, 0, 0, 100));

        ImageIcon up = new ImageIcon(PATH + "soundimage/up.png");
        JButton moveUp = new JButton();
        moveUp.setIcon(up);
        moveUp.setBounds(40, 15, 30,30);

        ImageIcon left = new ImageIcon(PATH + "soundimage/left.png");
        JButton moveLeft = new JButton();
        moveLeft.setIcon(left);
        moveLeft.setBounds(5, 50, 30,30);

        ImageIcon right = new ImageIcon(PATH + "soundimage/right.png");
        JButton moveRight = new JButton();
        moveRight.setIcon(right);
        moveRight.setBounds(75, 50, 30,30);

        ImageIcon down = new ImageIcon(PATH + "soundimage/down.png");
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
        Color doorColor1 = new Color(192, 128, 64);
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
        Color doorColor1 = new Color(192, 128, 64);
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

        JLabel userNameLabel = new JLabel("USER NAME:");
        Font boldFont = new Font(userNameLabel.getFont().getName(), Font.BOLD, userNameLabel.getFont().getSize());
        userNameLabel.setFont(boldFont);
        userNameLabel.setBounds(20,8,100,40);

        JLabel userPoints = new JLabel("POINTS:");
        Font boldFont2 = new Font(userNameLabel.getFont().getName(), Font.BOLD, userPoints.getFont().getSize());
        userPoints.setFont(boldFont2);
        userPoints.setBounds(190, 8, 100,40);

        JLabel userKeys = new JLabel("KEYS:");
        Font boldFont3 = new Font(userNameLabel.getFont().getName(), Font.BOLD, userKeys.getFont().getSize());
        userKeys.setFont(boldFont3);
        userKeys.setBounds(350, 8, 100,40);

        JLabel userStreak = new JLabel("STREAK:");
        Font boldFont4 = new Font(userNameLabel.getFont().getName(), Font.BOLD, userStreak.getFont().getSize());
        userStreak.setFont(boldFont4);
        userStreak.setBounds(480, 8, 100,40);


        myUserPanel.add(userKeys);
        myUserPanel.add(userStreak);
        myUserPanel.add(userPoints);
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
        myBackGroundIcon = new ImageIcon(PATH + "soundimage/Background2.jpeg");
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
