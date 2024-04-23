package src.Model;

import java.util.Random;

public class QuestionFactory {
    private Random random;

    public QuestionFactory() {
        random = new Random();
    }

    public Question createQuestion(String category) {
        int choice = random.nextInt(3); // 0 for Multiple Choice, 1 for True/False, 2 for Short Answer
        return switch (choice) {
            case 0 -> createMultipleChoiceQuestion(category);
            case 1 -> createTrueFalseQuestion(category);
            case 2 -> createShortAnswerQuestion(category);
            default -> null; // Should never happen
        };
    }

    public Question createMultipleChoiceQuestion(String category) {
        return null;
    }

    public Question createTrueFalseQuestion(String category) {
        return null;
    }

    public Question createShortAnswerQuestion(String category) {
        return null;
    }
}
