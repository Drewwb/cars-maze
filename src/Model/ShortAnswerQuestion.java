package src.Model;

public class ShortAnswerQuestion {
    private final String correctAnswer;

    public ShortAnswerQuestion(String text, String correctAnswer, String category) {
        super();
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(String userAnswer) {
        return correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }
}
