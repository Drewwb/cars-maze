package src.Model;

public class Maze {
    private Room[][] myRooms;

    public Maze() {
        myRooms = new Room[8][8];
        initializeRooms();
    }

    private void initializeRooms() {
        for(int i = 0; i < myRooms.length; i++) {
            for(int j = 0; j < myRooms[i].length; j++) {
                myRooms[i][j] = new Room();
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
}
