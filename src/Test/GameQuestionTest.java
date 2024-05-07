package src.Test;

import src.Model.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameQuestionTest {

    @Test
    void testQuestionDetails() {
        // Hardcoded values for ID and expected outcomes
        int testId = 2; // Assuming an ID that exists in your DB with known values
        String expectedQuestion = "What was the first car brand to ever be created?";
        String expectedAnswer = "Benz";
        String expectedCategory = "Cars";

        // Create a question object with the test ID
        Question question = new Question("MultipleChoice", testId);

        // Assert that the fetched details match the expected values
        assertEquals(expectedQuestion, question.getQuestion(), "Questions do not match");
        assertEquals(expectedAnswer, question.getAnswer(), "Answers do not match");
        assertEquals(expectedCategory, question.getCategory(), "Categories do not match");
    }


    @Test
    void testSetIdValid() {
        Question question = new Question("Math", 10); // Assuming 10 is a valid ID within the range.
        question.setId(5); // Also a valid ID.
        assertEquals(5, question.getId(), "The ID should be set to 5.");
    }

    @Test
    void testSetIdInvalidLowerBound() {
        Question question = new Question("Math", 10); // Assuming 10 is a valid ID.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            question.setId(0); // Assuming 0 is an invalid ID, below the lower bound.
        });
        assertEquals("Invalid ID", exception.getMessage(), "Exception message should indicate invalid ID.");
    }

    @Test
    void testSetIdInvalidUpperBound() {
        Question question = new Question("Math", 10); // Assuming 10 is a valid ID.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            question.setId(30); // Assuming 30 is an invalid ID, above the upper bound.
        });
        assertEquals("Invalid ID", exception.getMessage(), "Exception message should indicate invalid ID.");
    }

    @Test
    void testSetIdInvalidNegative() {
        Question question = new Question("Math", 10); // Assuming 10 is a valid ID.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            question.setId(-1); // Assuming negative IDs are invalid.
        });
        assertEquals("Invalid ID", exception.getMessage(), "Exception message should indicate invalid ID.");
    }

    @Test
    void testNullType() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Question(null, 2);
        });
    }
}