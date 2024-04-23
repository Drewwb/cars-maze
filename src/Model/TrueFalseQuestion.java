package src.Model;

public class TrueFalseQuestion {
    private boolean correctAnswer;

    public TrueFalseQuestion(String text, boolean correctAnswer, String category) {
        super();
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(String userAnswer) {
        // For true/false questions, the userAnswer must be "true" or "false"
        return userAnswer.trim().equalsIgnoreCase(String.valueOf(correctAnswer));
    }
}
