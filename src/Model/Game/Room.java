package src.Model.Game;

public class Room {
    private Door[] doors;
    private boolean visited;
    private int myRoomNumber;
    final private int NORTH = 0;
    final private int EAST = 1;
    final private int SOUTH = 2;
    final private int WEST = 3;


    public Room(int roomNumber) {
        this.visited = false;
        this.myRoomNumber = roomNumber;

        if(roomNumber >= 10) {
            if(roomNumber == 10) {
                this.doors = new Door[2];
                Direction direction = Direction.values()[EAST];
                Direction direction2 = Direction.values()[SOUTH];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
            } else if(roomNumber == 11 || roomNumber == 12 || roomNumber == 13) {
                this.doors = new Door[3];
                Direction direction = Direction.values()[EAST];
                Direction direction2 = Direction.values()[SOUTH];
                Direction direction3 = Direction.values()[WEST];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
                doors[2] = new Door(direction3);
            } else if(roomNumber == 14) {
                this.doors = new Door[2];
                Direction direction = Direction.values()[SOUTH];
                Direction direction2 = Direction.values()[WEST];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
            } else if (roomNumber == 15) {
                this.doors = new Door[3];
                Direction direction = Direction.values()[NORTH];
                Direction direction2 = Direction.values()[EAST];
                Direction direction3 = Direction.values()[SOUTH];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
                doors[2] = new Door(direction3);
            } else if (roomNumber == 16 || roomNumber == 17 || roomNumber == 18) {
                this.doors = new Door[4];
                Direction direction = Direction.values()[NORTH];
                Direction direction2 = Direction.values()[EAST];
                Direction direction3 = Direction.values()[SOUTH];
                Direction direction4 = Direction.values()[WEST];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
                doors[2] = new Door(direction3);
                doors[3] = new Door(direction4);
            } else if (roomNumber == 19) {
                this.doors = new Door[3];
                Direction direction = Direction.values()[NORTH];
                Direction direction2 = Direction.values()[SOUTH];
                Direction direction3 = Direction.values()[WEST];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
                doors[2] = new Door(direction3);
            } else if (roomNumber == 20) {
                this.doors = new Door[2];
                Direction direction = Direction.values()[NORTH];
                Direction direction2 = Direction.values()[EAST];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
            } else if (roomNumber == 21 || roomNumber == 22 || roomNumber == 23) {
                this.doors = new Door[3];
                Direction direction = Direction.values()[NORTH];
                Direction direction2 = Direction.values()[EAST];
                Direction direction3 = Direction.values()[WEST];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
                doors[2] = new Door(direction3);
            } else if (roomNumber == 24) {
                this.doors = new Door[2];
                Direction direction = Direction.values()[NORTH];
                Direction direction2 = Direction.values()[WEST];
                doors[0] = new Door(direction);
                doors[1] = new Door(direction2);
            }
        }

//        if (roomNumber >= 10) {
//            if (roomNumber == 10 || roomNumber == 14 || roomNumber == 20 || roomNumber == 24) {
//                this.doors = new Door[2];
//
//            } else if (roomNumber == 11 || roomNumber == 12 || roomNumber == 13 || roomNumber == 21 || roomNumber == 22 || roomNumber == 23) {
//                this.doors = new Door[3];
//
//            } else {
//                this.doors = new Door[4];
//            }
//        } else {
//            // Default room with 4 doors
//            this.doors = new Door[4];
//        }
//
//        initializeDoors();
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

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    public int getRoomNumber() {
        return myRoomNumber;
    }
}
