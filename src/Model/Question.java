package src.Model;

public abstract class Question {
    private String text;
    private String answer;
    private String category;

    public Question(String text, String answer, String category) {
        this.text = text;
        this.answer = answer;
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCorrectAnswer(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer.trim());
    }
}
