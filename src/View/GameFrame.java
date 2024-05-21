package src.View;

import src.Model.Game.Direction;
import src.Model.Game.GameLogic;
import src.Model.Questions.MultipleChoiceQuestion;
import src.Model.Questions.Question;
import src.Model.Questions.QuestionFactory;
import src.Model.Questions.ShortAnswerQuestion;
import src.Model.Questions.TrueFalseQuestion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFrame implements ActionListener {
    private final String PATH = "";
    private JFrame myFrame;
    private JMenuBar myOptionBar;
    private JMenu myFile, myHelp;
    private JMenuItem mySaveGame, myLoadGame, myExitGame, myAbout, myGamePlayInstruction;
    private JPanel myUserPanel, mazePanel, controlPanel, spellPanel, questionPanel;
    private JLabel backGroundLabel;
    private ImageIcon myBackGroundIcon;
    private JRadioButton option1, option2, option3, option4;
    private JButton submitButton;
    private GameLogic gameLogic;

    public GameFrame() {
        gameLogic = new GameLogic();
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
        controlPanel.setBounds(10, 450, 340, 120);
        controlPanel.setBackground(new Color(0, 0, 0, 100));

        ImageIcon up = new ImageIcon(PATH + "soundimage/up.png");
        JButton moveUp = new JButton();
        moveUp.setIcon(up);
        moveUp.setBounds(40, 15, 30, 30);
        moveUp.addActionListener(e -> moveCharacter(Direction.NORTH));

        ImageIcon left = new ImageIcon(PATH + "soundimage/left.png");
        JButton moveLeft = new JButton();
        moveLeft.setIcon(left);
        moveLeft.setBounds(5, 50, 30, 30);
        moveLeft.addActionListener(e -> moveCharacter(Direction.WEST));

        ImageIcon right = new ImageIcon(PATH + "soundimage/right.png");
        JButton moveRight = new JButton();
        moveRight.setIcon(right);
        moveRight.setBounds(75, 50, 30, 30);
        moveRight.addActionListener(e -> moveCharacter(Direction.EAST));

        ImageIcon down = new ImageIcon(PATH + "soundimage/down.png");
        JButton moveDown = new JButton();
        moveDown.setIcon(down);
        moveDown.setBounds(40, 85, 30, 30);
        moveDown.addActionListener(e -> moveCharacter(Direction.SOUTH));

        controlPanel.add(moveUp);
        controlPanel.add(moveLeft);
        controlPanel.add(moveRight);
        controlPanel.add(moveDown);

        myFrame.add(controlPanel);
    }

    private void moveCharacter(Direction direction) {
        gameLogic.moveCharacter(direction);
        mazePanel.repaint(); // Repaint the maze panel to reflect the new position -> this is why key respawn very time we move
    }
    private void initializeMazePanel() {
        mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Fill the background with the translucent color
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());

                // we can do it inside the gameLogic class.
                int[][] maze = {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 10, 10, 2, 11, 11, 2, 12, 12, 2, 13, 13, 2, 14, 14, 1},
                        {1, 10, 10, 1, 11, 11, 1, 12, 12, 1, 13, 13, 1, 14, 14, 1},
                        {1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1},
                        {1, 15, 15, 1, 16, 16, 1, 17, 17, 1, 18, 18, 1, 19, 19, 1},
                        {1, 15, 15, 2, 16, 16, 2, 17, 17, 2, 18, 18, 2, 19, 19, 1},
                        {1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1},
                        {1, 20, 20, 1, 21, 21, 1, 22, 22, 1, 23, 23, 1, 24, 24, 1},
                        {1, 20, 20, 2, 21, 21, 2, 22, 22, 2, 23, 23, 2, 24, 24, 3},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };

                List<Integer> keyLocationRow = new ArrayList<>();
                List<Integer> keyLocationCol = new ArrayList<>();

                for (int i = 0; i < maze.length; i++) {
                    for (int j = 0; j < maze[i].length; j++) {
                        if (maze[i][j] >= 10) {
                            keyLocationRow.add(i);
                            keyLocationCol.add(j);
                        }
                    }
                }

                Random randRow = new Random();
                int randRowIndex = randRow.nextInt(keyLocationRow.size());
                int keyRowIndex = keyLocationRow.get(randRowIndex);

                Random randColumn = new Random();
                int randColumnIndex = randColumn.nextInt(keyLocationCol.size());
                int keyColumnIndex = keyLocationCol.get(randColumnIndex);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the size of each cell
                int cellWidth = panelWidth / maze[0].length;
                int cellHeight = panelHeight / maze.length;


                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        if (maze[row][col] == 1) {
                            drawBrickWall(g, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                        } else if (maze[row][col] == 2) {
                            displayDoorImage(g, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                        } else if (maze[row][col] == 3) {
                            displayExitDoor(g, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                        }

                        // Display the character
                        if (row == gameLogic.getCharacterRow() && col == gameLogic.getCharacterCol()) {
                            displayCharacter(g, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                        }

                        g.setColor(Color.GRAY); // Drawing the grid lines
                        g.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                    }
                }
                displayKey(g, cellWidth, cellHeight);
            }
        };
        mazePanel.setLayout(null);
        mazePanel.setBorder(new LineBorder(Color.darkGray, 3));
        mazePanel.setBounds(100, 80, 550, 350);
        mazePanel.setBackground(new Color(0, 0, 0, 100));
        mazePanel.setOpaque(false);

        myFrame.add(mazePanel);
    }

    private void drawBrickWall(Graphics g, int x, int y, int width, int height) {
        int brickWidth = width / 2;
        int brickHeight = height / 4;
        Color brickColor1 = new Color(100, 100, 100);
        Color brickColor2 = new Color(80, 80, 80);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 2; col++) {
                int brickX = x + col * brickWidth + (row % 2 == 0 ? 0 : brickWidth / 2);
                int brickY = y + row * brickHeight;

                g.setColor((row + col) % 2 == 0 ? brickColor1 : brickColor2);
                g.fillRect(brickX, brickY, brickWidth, brickHeight);

                g.setColor(Color.BLACK); // Brick border color
                g.drawRect(brickX, brickY, brickWidth, brickHeight);
            }
        }
    }


    private void displayDoorImage(Graphics g, int x, int y, int width, int height) {
        // Load the door image (replace "doorImage.jpg" with the path to your image file)
        ImageIcon icon = new ImageIcon("door.png");
        Image doorImage = icon.getImage();

        // Draw the door image at the specified position and size
        g.drawImage(doorImage, x, y, width, height, null);
    }

    private void displayExitDoor(Graphics g, int x, int y, int width, int height) {
        // Load the door image (replace "doorImage.jpg" with the path to your image file)
        ImageIcon icon = new ImageIcon("exitDoor.png");
        Image doorImage = icon.getImage();

        // Draw the door image at the specified position and size
        g.drawImage(doorImage, x, y, width, height, null);
    }
    private void displayKey(Graphics g, int width, int height) {
        // Load the door image (replace "doorImage.jpg" with the path to your image file)
        ImageIcon icon = new ImageIcon("key.png");
        Image doorImage = icon.getImage();
        int[] keyCoords = gameLogic.getKeyLocation();

        // Draw the door image at the specified position and size
        g.drawImage(doorImage, keyCoords[0] * width, keyCoords[1] * height, width, height, null);
    }
    private void displayCharacter(Graphics g, int x, int y, int width, int height) {
        // Load the door image (replace "doorImage.jpg" with the path to your image file)
        ImageIcon icon = new ImageIcon("character.png");
        Image doorImage = icon.getImage();

        // Draw the door image at the specified position and size
        g.drawImage(doorImage, x, y, width, height, null);
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
