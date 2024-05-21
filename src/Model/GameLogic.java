package src.Model;

public class GameLogic {
    private int points;
    private boolean answerCorrect;
    private boolean gameOver;
    private Maze myMaze;
    private int characterRow;
    private int characterCol;
    private int[] keyLocation;


    public GameLogic() {
        myMaze = new Maze(); // Maze that fully loaded
        keyLocation = myMaze.getKeyCoordinates();
        this.points = 0;
        this.answerCorrect = false;
        this.gameOver = false;
        characterSpawn();
    }

    // Spawn character at the starting point, e.g., (1, 1)
    private void characterSpawn() {
        this.characterRow = 1;
        this.characterCol = 1;
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
                break;
            case EAST:
                newCol++;
                break;
            case SOUTH:
                newRow++;
                break;
            case WEST:
                newCol--;
                break;
        }

        // Check if the new position is valid (e.g., not out of bounds and not a wall)
        if (isValidPosition(newRow, newCol)) {
            characterRow = newRow;
            characterCol = newCol;
        } else if (isDoor(newRow, newCol)) {
            interactWithDoor(direction);
        }
    }
    // Check if the position is a door
    private boolean isDoor(int row, int col) {
        return myMaze.getRooms()[row][col].getRoomNumber() == 2; // Assuming 2 is a door
    }

    // Check if the position is valid
    private boolean isValidPosition(int row, int col) {
        if (row < 0 || row >= myMaze.getRooms().length || col < 0 || col >= myMaze.getRooms()[0].length) {
            return false;
        }
        return myMaze.getRooms()[row][col].getRoomNumber() != 1; // Assuming 1 is a wall
    }

    public void interactWithDoor(Direction direction) {
        // Get the current room
        Room currentRoom = myMaze.getRooms()[characterRow][characterCol];
        Door[] doors = currentRoom.getDoors();

        for (Door door : doors) {
            if (door.getMyDirection() == direction) {
                // Ask the question
                Question question = door.getMyQuestion();
                // Simulate answering the question
                boolean answerCorrect = answerQuestion(question);

                if (answerCorrect) {
                    door.setDoorLock(false); // Unlock the door
                    incrementPoints(10); // Increment points
                    setAnswerStatement(true);
                } else {
                    setAnswerStatement(false);
                }
                break;
            }
        }
    }

    // Placeholder method for answering a question
    private boolean answerQuestion(Question question) {
        // nothing here yet, it's 12PM I'm cooked ~~~
        return true;
    }
}
