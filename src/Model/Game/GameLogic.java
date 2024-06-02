package src.Model.Game;

import src.Model.Questions.Question;

public class GameLogic {
    private String userName;
    private int myPoints;
    private int myStreak;
    private int myHearts;
    private int myKeys;
    private boolean gameOver;
    private Maze myMaze;
    private int characterRow;
    private int characterCol;
    private int[] keyLocation; // coordinate for key 0 - row / 1 - col
    private int currentRoomNumber; // Store the current room number

    private Question currentQuestion;
    private boolean isDoorCheck;
    private Direction currentDirection;
    private boolean hasKey;
    private int[] exitDoorLocation;
    private boolean playerWin;


    public GameLogic() {
        currentDirection = null;
        myMaze = new Maze(); // Maze that fully loaded
        keyLocation = myMaze.getKeyCoordinates();
        System.out.println(keyLocation[0]); // CHECK
        System.out.println(keyLocation[1]); // CHECK
        this.currentQuestion = null;
        this.isDoorCheck = false;
        this.myPoints = 0;
        this.myStreak = 0;
        this.myHearts = 3; // user Health is 3
        this.myKeys = 0;
        this.gameOver = false;
        this.playerWin = false;
        this.hasKey = false;
        characterSpawn();
        currentRoomNumber = myMaze.getCurrentValue(characterRow, characterCol); // Initialize the current room number
    }
    public GameLogic getInstance(){
        return this;
    }

    // Spawn character at the starting point, e.g., (1, 1)
    private void characterSpawn() {
        this.characterRow = 1;
        this.characterCol = 1;
        currentRoomNumber = myMaze.getCurrentValue(characterRow, characterCol); // Initialize the current room number
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return this.userName;
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
        return this.myPoints;
    }
    public void setPoints(int points) {
        this.myPoints = points;
    }
    public void incrementPoints() {
        this.myPoints += 10;
    }
    public int getMyStreak() {
        return myStreak;
    }
    public void setMyStreak(int myStreak) {
        this.myStreak = myStreak;
    }
    public void incrementStreak() {
        this.myStreak++;
    }
    public void decrementPoints() {
        this.myPoints -= 5;

        if(this.myPoints < 0) {
            this.setPoints(0);
        }
    }
    public int getMyHearts() {
        return myHearts;
    }
    public void decrementMyHearts() {
        this.myHearts--;
    }
    public void incrementKeys() {
        myKeys++;
    }
    public void decrementKeys() {
        myKeys--;
    }
    public int getMyKeys() {
        return myKeys;
    }
    public void setMyKeys(int myKeys) {
        this.myKeys = myKeys;
    }


    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public boolean getGameOver() {
        return this.gameOver;
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
        if(checkSurrounded(myMaze.getLayout(), characterRow, characterCol)) {
            // Lose the game.
            this.setGameOver(true);
            System.out.println("Game Over Bitch!");
        }
        if(myMaze.getCurrentValue(characterRow, characterCol) == 3) {
            this.setPlayerWin(true);
            System.out.println("Player Win!!!");
        }
        if(this.getMyHearts() <= 0) {
            this.setGameOver(true);
        }

        isKeyAtThisLocation(characterRow, characterCol);
        if(hasKey) {
            incrementKeys();
            hasKey = false;
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
    public void setPlayerWin(boolean playerWin) {
        this.playerWin = playerWin;
    }
    public boolean getPlayerWin() {
        return playerWin;
    }


    // GAME LOGIC FOR LOSE/WIN

    public boolean checkSurrounded(int[][] board, int characterRow, int characterCol) {
        // Check position
        int position = 0;

        if(isInScope(board[characterRow + 1][characterCol]) && isInScope(board[characterRow][characterCol + 1])) {
            position = 1;
        } else if(isInScope(board[characterRow + 1][characterCol]) && isInScope(board[characterRow][characterCol - 1])) {
            position = 2;
        } else if(isInScope(board[characterRow - 1][characterCol]) && isInScope(board[characterRow][characterCol + 1])) {
            position = 3;
        } else if(isInScope(board[characterRow - 1][characterCol]) && isInScope(board[characterRow][characterCol - 1])) {
            position = 4;
        }
        int count = 0;
        if(position == 1) {
            if(checkCase(board, characterRow, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow, characterCol + 1) == 5) {
                count++;
            }
            if(checkCase(board, characterRow + 1, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow + 1, characterCol + 1) == 5) {
                count++;
            }
        } else if(position == 2) {
            if(checkCase(board, characterRow, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow, characterCol - 1) == 5) {
                count++;
            }
            if(checkCase(board, characterRow + 1, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow + 1, characterCol - 1) == 5) {
                count++;
            }
        } else if(position == 3) {
            if(checkCase(board, characterRow, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow - 1, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow, characterCol + 1) == 5) {
                count++;
            }
            if(checkCase(board, characterRow - 1, characterCol + 1) == 5) {
                count++;
            }
        } else if(position == 4) {
            if(checkCase(board, characterRow, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow - 1, characterCol) == 5) {
                count++;
            }
            if(checkCase(board, characterRow, characterCol - 1) == 5) {
                count++;
            }
            if(checkCase(board, characterRow - 1, characterCol - 1) == 5) {
                count++;
            }
        }
        System.out.println("Position: " + position);
        System.out.println("Count: " + count);
        return count == 4;
    }

    private boolean isInScope(int value) {
        return value >= 10 && value <= 25;
    }

    public int checkCase(int[][] board, int characterRow, int characterCol) {
        int count = 0;
        if (board[characterRow - 1][characterCol] == 1) { // check top
            count++;
            System.out.println("Check 1");
        }
        if (board[characterRow + 1][characterCol] == 1) { // check bottom
            count++;
            System.out.println("Check 2");
        }
        if (board[characterRow][characterCol - 1] == 1) { // check left
            count++;
            System.out.println("Check 3");
        }
        if (board[characterRow][characterCol + 1] == 1) { // check right
            count++;
            System.out.println("Check 4");
        }
        if (board[characterRow - 1][characterCol - 1] == 1) { // check left corner upper
            count++;
            System.out.println("Check 5");
        }
        if (board[characterRow - 1][characterCol + 1] == 1) { // check right corner upper
            count++;
            System.out.println("Check 6");
        }
        if (board[characterRow + 1][characterCol - 1] == 1) { // check right corner lower
            count++;
            System.out.println("Check 7");
        }
        if (board[characterRow + 1][characterCol + 1] == 1) { // check left corner right
            count++;
            System.out.println("Check 8");
        }
        return count;
    }
    public boolean doesCharacterHaveKey() { //getter method
        return hasKey;
    }
    private void isKeyAtThisLocation(int row, int col) {
        int keyRow = keyLocation[1];
        int keyCol = keyLocation[0];
        if(row == keyRow && col == keyCol) {
            hasKey = true;
            // set the key coordinate to 0, 0 so user can not access it no more.
            keyLocation[1] = 0;
            keyLocation[0] = 0;
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
