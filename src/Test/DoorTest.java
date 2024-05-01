package src.Test;
import src.Model.Door;
import static org.junit.Assert.*;
import src.Model.Direction;

public class DoorTest {

    @org.junit.Test
    public void setDirection() {
        Door testDoor = new Door(Direction.WEST);
        assertEquals(Direction.WEST, testDoor.getMyDirection());
    }

    @org.junit.Test
    public void getMyDirection() {
    }

    @org.junit.Test
    public void setQuestion() {
    }

    @org.junit.Test
    public void getMyQuestion() {
    }

    @org.junit.Test
    public void setDoorLock() {
    }
}