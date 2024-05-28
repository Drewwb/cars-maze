package src.View;

import src.Model.Game.Direction;
import src.Model.Game.GameLogic;
import src.Model.Questions.MultipleChoiceQuestion;
import src.Model.Questions.Question;
import src.Model.Questions.ShortAnswerQuestion;
import src.Model.Questions.TrueFalseQuestion;

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
    private GameLogic gameLogic;
    private JTextField myUserName, myPoints, myKeys, mySteak;

    public GameFrame() {
        gameLogic = new GameLogic();
        initializeFrame();
        initializeUserPanel(); // need to come first before set background
        initializeMazePanel();
        initializeControlPanel();
        initializeSpellPanel();
        //initializeQuestionPanel();

        initializeBackGround();
        initializeOptionBar();

    }
    private void initializeQuestionPanel(Question currentQuestion) {
        if (questionPanel != null) {
            myFrame.remove(questionPanel);
        }

        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(360, 450, 365, 225);
        questionPanel.setBackground(new Color(240, 240, 240));

        if (currentQuestion instanceof MultipleChoiceQuestion) {
            initializeMCQuestionPanel(currentQuestion);
        } else if (currentQuestion instanceof TrueFalseQuestion) {
            initializeTFQuestionPanel(currentQuestion);
        } else if (currentQuestion instanceof ShortAnswerQuestion) {
            initializeSAQQuestionPanel(currentQuestion);
        }

        questionPanel.revalidate(); // Let's fucking goooo!
        questionPanel.repaint();
        myFrame.add(questionPanel);
    }

    private void initializeMCQuestionPanel(Question question) {
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(360, 450, 365, 225);
        questionPanel.setBackground(new Color(240, 240, 240));
        questionPanel.setVisible(true);

        // Type cast question
        MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;

        JLabel questionLabel = new JLabel("<html><div style='width: 300px;'>" + mcQuestion.getQuestion() + "</div></html>");
        questionLabel.setBounds(10, 10, 335, 30);

        String[] options = mcQuestion.getOptions();
        option1 = new JRadioButton(options[0]);
        option2 = new JRadioButton(options[1]);
        option3 = new JRadioButton(options[2]);
        option4 = new JRadioButton(options[3]);

        option1.setBounds(10, 50, 150, 30);
        option2.setBounds(10, 90, 150, 30);
        option3.setBounds(10, 130, 150, 30);
        option4.setBounds(10, 170, 150, 30);

        option1.setOpaque(false);
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
        submitButton.setBounds(200, 170, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correctAnswer = mcQuestion.getAnswer();
                boolean isCorrect = false;

                if (option1.isSelected()) {
                    isCorrect = option1.getText().equals(correctAnswer);
                } else if (option2.isSelected()) {
                    isCorrect = option2.getText().equals(correctAnswer);
                } else if (option3.isSelected()) {
                    isCorrect = option3.getText().equals(correctAnswer);
                } else if (option4.isSelected()) {
                    isCorrect = option4.getText().equals(correctAnswer);
                }

                int newRow = gameLogic.getCharacterRow();
                int newCol = gameLogic.getCharacterCol();

                if (gameLogic.getCurrentDirection() == Direction.NORTH) {
                    newRow--;
                } else if (gameLogic.getCurrentDirection() == Direction.SOUTH) {
                    newRow++;
                } else if (gameLogic.getCurrentDirection() == Direction.WEST) {
                    newCol--;
                } else if (gameLogic.getCurrentDirection() == Direction.EAST) {
                    newCol++;
                }

                if (isCorrect) {
                    JOptionPane.showMessageDialog(null, "Congrats! Correct Answer");
                    gameLogic.incrementPoints(10); // Add points for a correct answer
                    gameLogic.getMyMaze().setCurrentValue(newRow, newCol, 100); // Update maze value
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Answer!");
                    gameLogic.getMyMaze().setCurrentValue(newRow, newCol, -1);
                }

                gameLogic.setCurrentRow(newRow);
                gameLogic.setCurrentCol(newCol);
                gameLogic.setCurrentQuestion(null); // To not display again after repaint
                questionPanel.setVisible(false);
                mazePanel.repaint(); // Repaint maze after moving the character
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


    private void initializeSAQQuestionPanel(Question question) {
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBorder(new LineBorder(Color.darkGray, 3));
        questionPanel.setBounds(360, 450, 365, 225);
        questionPanel.setBackground(new Color(240, 240, 240));
        questionPanel.setVisible(true);

        ShortAnswerQuestion saqQuestion = (ShortAnswerQuestion) question;
        String theQuestion = saqQuestion.getQuestion();

        JLabel questionLabel = new JLabel("<html><div style='width: 300px;'>" + theQuestion + "</div></html>");
        questionLabel.setBounds(10, 10, 335, 30);

        JTextField textField = new JTextField(20);
        textField.setBounds(10, 50, 150, 30);
        textField.setOpaque(false);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 170, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correctAnswer = saqQuestion.getAnswer();
                String userAnswer = textField.getText().trim(); // Trim to remove leading/trailing spaces
                boolean isCorrect = correctAnswer.equalsIgnoreCase(userAnswer);

                if (isCorrect) {
                    JOptionPane.showMessageDialog(null, "Congrats! Correct Answer");

                    // Update character position after correct answer
                    int newRow = gameLogic.getCharacterRow();
                    int newCol = gameLogic.getCharacterCol();
                    // Determine the new position based on the current direction
                    switch (gameLogic.getCurrentDirection()) {
                        case NORTH:
                            newRow--;
                            break;
                        case SOUTH:
                            newRow++;
                            break;
                        case EAST:
                            newCol++;
                            break;
                        case WEST:
                            newCol--;
                            break;
                    }
                    // Update character position in the maze
                    gameLogic.setCurrentRow(newRow);
                    gameLogic.setCurrentCol(newCol);
                    gameLogic.getMyMaze().setCurrentValue(newRow, newCol, 100);
                    gameLogic.setCurrentQuestion(null);

                    questionPanel.setVisible(false);
                    mazePanel.repaint(); // Repaint maze after moving the character
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
        questionPanel.setVisible(true);
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
                String correctAnswer = tfQuestion.getAnswer().trim().toLowerCase();
                boolean isCorrect;
                String userAnswer = "";

                if (option1.isSelected()) {
                    userAnswer = "true";
                } else if (option2.isSelected()) {
                    userAnswer = "false";
                }

                // Normalize the userAnswer by trimming and converting to lowercase
                isCorrect = correctAnswer.equals(userAnswer.trim().toLowerCase());

                if (isCorrect) {
                    JOptionPane.showMessageDialog(null, "Congrats! Correct Answer");

                    // Update character position based on the current direction
                    int newRow = gameLogic.getCharacterRow();
                    int newCol = gameLogic.getCharacterCol();

                    switch (gameLogic.getCurrentDirection()) {
                        case NORTH:
                            newRow--;
                            break;
                        case SOUTH:
                            newRow++;
                            break;
                        case EAST:
                            newCol++;
                            break;
                        case WEST:
                            newCol--;
                            break;
                    }

                    gameLogic.setCurrentRow(newRow);
                    gameLogic.setCurrentCol(newCol);
                    gameLogic.getMyMaze().setCurrentValue(newRow, newCol, 100);
                    gameLogic.setCurrentQuestion(null);

                    questionPanel.setVisible(false);
                    mazePanel.repaint();
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
        gameLogic.moveCharacter(direction); // it has all logic with door interact here
        mazePanel.repaint();

        Question currentQuestion = gameLogic.getCurrentQuestion();

        initializeQuestionPanel(currentQuestion);
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
                int[][] maze = gameLogic.getMyMaze().getLayout();

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
                        } else if (maze[row][col] == 100) {
                            displayCheckImage(g, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                        } else if (maze[row][col] == -1) {
                            drawRedX(g, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
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
    private void drawRedX(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.RED);
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x + width, y, x, y + height);
    }
    private void displayCheckImage(Graphics g, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon("checkIcon.png");
        Image checkImage = icon.getImage();

        g.drawImage(checkImage, x, y, width, height, null);
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

//        myUserName = new JTextField();
//        myUserName.setBounds(30,20, 100, 40);
//        myUserName.setEditable(false);


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

        //myUserName.add(myUserName);
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
