package src.Model;

import java.io.Serializable;

public class Maze implements Serializable {
    private Room[][] myRooms;

    public Maze() {
        myRooms = new Room[6][6];
        initializeRooms();
    }

    private void initializeRooms() {
        for(int i = 0; i < myRooms.length; i++) {
            for(int j = 0; j < myRooms[i].length; j++) {
                Room room = new Room(); // each room would have it own 4 questions
                myRooms[i][j] = room;
            }
        }
    }

    public Room[][] getMyRooms() {
        return myRooms;
    }

    public Room getRoom(int row, int column) {
        if(row >= 0 && row < myRooms.length && column >= 0 && column < myRooms[0].length) {
            return myRooms[row][column];
        }
        return null;
    }

    public void setRoom(int row, int column, Room room) {
        if(row >= 0 && row < myRooms.length && column >= 0 && column < myRooms[0].length) {
            myRooms[row][column] = room;
        }
    }
    // Method to save player data with a specific filename
    public void save() {
        SaveData.saveGame(this, "game_data.dat");
    }

    // Method to load player data with a specific filename
    public static Maze load() {
        return (Maze) SaveData.loadGame("game_data.dat");
    }
}
