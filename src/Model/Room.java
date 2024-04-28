package src.Model;

public class Room {
    private Door[] myDoors;
    private boolean isVisited;

    public Room() {

        this.myDoors = new Door[4]; // create a doors array size 4.
        initializeDoor();
        this.isVisited = false;
    }
    public void initializeDoor() {
        for(int i = 0; i < myDoors.length; i++) {
            if(i == 0) {
                Direction north = Direction.NORTH;
                Door door1 = new Door(north);
                myDoors[i] = door1;
            } else if(i == 1) {
                Direction west = Direction.WEST;
                Door door2 = new Door(west);
                myDoors[i] = door2;
            } else if(i == 2) {
                Direction south = Direction.EAST;
                Door door3 = new Door(south);
                myDoors[i] = door3;
            } else {
                Direction south = Direction.SOUTH;
                Door door4 = new Door(south);
                myDoors[i] = door4;
            }
        }
    }
    public void setVisited(boolean theVisit) {
        this.isVisited = theVisit;
    }
    public boolean getVisited() {
        return this.isVisited;
    }
    public void setDoor(int index, Door door) {
        if(index >= 0 && index < myDoors.length) {
            myDoors[index] = door;
        }
    }
    public Door getDoor(int index) {
        if(index >= 0 && index < myDoors.length) {
            return myDoors[index];
        }
        return null;
    }
}

