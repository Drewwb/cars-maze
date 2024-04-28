package src.Model;

import java.util.Random;

public class QuestionFactory {
    private Random random;

    public QuestionFactory() {
        random = new Random();
    }

    public Question createQuestion() {
        int choice = random.nextInt(3); // 0 for Multiple Choice, 1 for True/False, 2 for Short Answer
        return switch (choice) {
            case 0 -> createMultipleChoiceQuestion();
            case 1 -> createTrueFalseQuestion();
            case 2 -> createShortAnswerQuestion();
            default -> null; // Should never happen
        };
    }

    public Question createMultipleChoiceQuestion() {
        return null;
    }

    public Question createTrueFalseQuestion() {
        return null;
    }

    public Question createShortAnswerQuestion() {
        return null;
    }
}
