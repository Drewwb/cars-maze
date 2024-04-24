package src.Model;

public class Room {
    private Door[] myDoors;
    private boolean isVisited;

    public Room() {
        this.myDoors = new Door[4];
        this.isVisited = false;
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

