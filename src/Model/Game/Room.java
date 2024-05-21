package src.Model.Game;

public class Room {
    private Door[] doors;
    private boolean visited;
    private int myRoomNumber;

    public Room(int roomNumber) {
        this.visited = false;
        this.myRoomNumber = roomNumber;

        if (roomNumber >= 10) {
            if (roomNumber == 10 || roomNumber == 14 || roomNumber == 20 || roomNumber == 24) {
                this.doors = new Door[2];
            } else if (roomNumber == 11 || roomNumber == 12 || roomNumber == 13 || roomNumber == 21 || roomNumber == 22 || roomNumber == 23) {
                this.doors = new Door[3];
            } else {
                this.doors = new Door[4];
            }
        } else {
            // Default room with 4 doors
            this.doors = new Door[4];
        }

        initializeDoors();
    }

    private void initializeDoors() {
        for (int i = 0; i < doors.length; i++) {
            Direction direction = Direction.values()[i];
            doors[i] = new Door(direction);
        }
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Door[] getDoors() {
        return doors;
    }

    public int getRoomNumber() {
        return myRoomNumber;
    }
}
