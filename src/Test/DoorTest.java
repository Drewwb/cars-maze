package src.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Model.Direction;
import src.Model.Door;
import src.Model.Question;
import src.Model.QuestionFactory;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {
    private Door door;

    @BeforeEach
    void setUp() {
        door = new Door(Direction.NORTH);
    }

    @Test
    void testInitialDirection() {
        assertEquals(Direction.NORTH, door.getMyDirection());
    }

    @Test
    void testSetDirection() {
        door.setDirection(Direction.EAST);
        assertEquals(Direction.EAST, door.getMyDirection());
    }

    @Test
    void testInitialDoorLock() {
        assertTrue(door.isDoorLocked());
    }

    @Test
    void testSetDoorLock() {
        door.setDoorLock(false);
        assertFalse(door.isDoorLocked());
    }

    @Test
    void testInitialQuestion() {
        assertNotNull(door.getMyQuestion());
    }

    @Test
    void testSetQuestion() {
        QuestionFactory factory = new QuestionFactory();
        Question newQuestion = factory.createMultipleChoiceQuestion();
        door.setQuestion(newQuestion);
        assertEquals(newQuestion, door.getMyQuestion());
    }
}