package src.Model.Game;

import src.Model.Questions.Question;

public class GameLogic {
    private int points;
    private boolean answerCorrect;
    private boolean gameOver;
    private Maze myMaze;
    private int characterRow;
    private int characterCol;
    private int[] keyLocation;
    private int currentRoomNumber; // Store the current room number
    private Question currentQuestion;
    private boolean isDoorCheck;
    private Direction currentDirection;
    private boolean hasKey;
    private int[] exitDoorLocation;

    public GameLogic() {
        currentDirection = null;
        myMaze = new Maze(); // Maze that fully loaded
        keyLocation = myMaze.getKeyCoordinates();
        System.out.println(keyLocation[0]);
        System.out.println(keyLocation[1]);
        this.currentQuestion = null;
        this.isDoorCheck = false;
        this.points = 0;
        this.answerCorrect = false;
        this.gameOver = false;
        characterSpawn();
        currentRoomNumber = myMaze.getCurrentValue(characterRow, characterCol); // Initialize the current room number
    }

    // Spawn character at the starting point, e.g., (1, 1)
    private void characterSpawn() {
        this.characterRow = 1;
        this.characterCol = 1;
        currentRoomNumber = myMaze.getCurrentValue(characterRow, characterCol); // Initialize the current room number
    }

    public int[] getKeyLocation() {
        return keyLocation;
    }

    public int getCharacterRow() {
        return characterRow;
    }

    public int getCharacterCol() {
        return characterCol;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getAnswerStatement() {
        return this.answerCorrect;
    }

    public void setAnswerStatement(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public void incrementPoints(int points) {
        this.points += points;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    // Move character in the maze
    public void moveCharacter(Direction direction) {
        int newRow = characterRow;
        int newCol = characterCol;

        switch (direction) {
            case NORTH:
                newRow--;
                currentDirection = Direction.NORTH;
                break;
            case EAST:
                newCol++;
                currentDirection = Direction.EAST;
                break;
            case SOUTH:
                newRow++;
                currentDirection = Direction.SOUTH;
                break;
            case WEST:
                newCol--;
                currentDirection = Direction.WEST;
                break;
        }

        System.out.println("Trying to move to row: " + newRow + ", col: " + newCol);
        System.out.println("Current maze value at new position: " + myMaze.getCurrentValue(newRow, newCol));

        if (isValidPosition(newRow, newCol)) {
            characterRow = newRow;
            characterCol = newCol;
            isDoorCheck = false;
            currentRoomNumber = myMaze.getCurrentValue(characterRow, characterCol); // Update current room number
        } else if (isDoor(newRow, newCol)) {
            System.out.println("HITTING THE DOOR");
            System.out.println("Encountered a door at row: " + newRow + ", col: " + newCol);
            interactWithDoor(direction, newRow, newCol, currentRoomNumber); // Use the stored room number
        } else {
            System.out.println("Invalid move. Position is either out of bounds or a wall.");
        }
    }

    public boolean isDoor(int row, int col) {
        int currentValue = myMaze.getCurrentValue(row, col);
        if(currentValue == 2) {
            isDoorCheck = true;
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidPosition(int row, int col) {
        if (row < 0 || row >= myMaze.getRooms().length || col < 0 || col >= myMaze.getRooms()[0].length) {
            return false;
        }
        return myMaze.getCurrentValue(row, col) != 1 && myMaze.getCurrentValue(row, col) != 2; // Assuming 1 is a wall and 2 is a door
    }

    public void interactWithDoor(Direction direction, int row, int col, int currentRoomNumber) {
        // check to see if the door is locked or not
        int currentValue = myMaze.getCurrentValue(row, col);
        if(currentValue == -1) {
            System.out.println("The door is locked!");
        }

        // Get the current room
        System.out.println("Current room number: " + currentRoomNumber);
        Room currentRoom = null;
        for (Room room : myMaze.getMyRoomList()) {
            if (currentRoomNumber == room.getRoomNumber()) {
                currentRoom = room;
                break; // found the room, no need to continue loop
            }
        }

        if (currentRoom == null) {
            System.out.println("No room found for the current position.");
            return;
        }

        Door[] doors = currentRoom.getDoors();
        boolean doorFound = false;

        for (Door door : doors) {
            if (door.getMyDirection() == direction) {
                doorFound = true;
                Question question = door.getMyQuestion();
                this.currentQuestion = question;

                System.out.println("The question for this door is: " + question.getQuestion());
                break;
            }
        }

        if (!doorFound) {
            System.out.println("No door found in the specified direction.");
        }
    }
    public Question getCurrentQuestion() {
        return currentQuestion;
    }
    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
    public boolean getIsDoorCheck() {
        return isDoorCheck;
    }
    public Maze getMyMaze() {
        return myMaze;
    }
    public void setCurrentRow(int row) {
        this.characterRow = row;
    }
    public void setCurrentCol(int col) {
        this.characterCol= col;
    }
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public boolean doesCharacterHaveKey() { //getter method
        return hasKey;
    }

    private void isKeyAtThisLocation(int row, int col) {
        int keyRow = keyLocation[1];
        int keyCol = keyLocation[0];
        if(row == keyRow && col == keyCol) {
            hasKey = true;
            System.out.println("USER PICKED UP KEY");
        }
    }

    private void isExitDoorAtThisLocation(int row, int col){
        if(row == exitDoorLocation[0] && col == exitDoorLocation[1]) { //true if exit door is there
            if(doesCharacterHaveKey()) {
                System.out.println("Congrats, you win!");
                gameOver = true;
            } else {
                System.out.println("You need the key before entering.");
            }
        }
    }
}
