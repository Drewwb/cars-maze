package src.Model;

import java.util.ArrayList;

public class MultipleChoiceQuestion {
    private ArrayList<String> options;
    private int correctIndex;

    public MultipleChoiceQuestion(String text, ArrayList<String> options, int correctIndex, String category) {
        super();
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public boolean isCorrectAnswer(String userAnswer) {
        // For multiple choice questions, the userAnswer must match the correct option exactly
        return userAnswer.trim().equalsIgnoreCase(options.get(correctIndex));
    }
}
