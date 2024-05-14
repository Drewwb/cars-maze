package src.Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class QuestionFactory {
    private final Random random;

    public QuestionFactory() {
        this.random = new Random();
    }

    public Question createQuestion() {
        int choice = random.nextInt(3);
        return switch (choice) {
            case 0 -> createMultipleChoiceQuestion();
            case 1 -> createTrueFalseQuestion();
            case 2 -> createShortAnswerQuestion();
            default -> null;
        };
    }

    public Question createMultipleChoiceQuestion() {
        return new MultipleChoiceQuestion(randomID());
    }

    public Question createTrueFalseQuestion() {
        return new TrueFalseQuestion(randomID());
    }

    public Question createShortAnswerQuestion() {
        return new ShortAnswerQuestion(randomID());
    }

    private int randomID() {
        Random rand = new Random();
        return rand.nextInt(9) + 1;
    }

    private static boolean isIdInQuestionsAsked(int id) {
        // SQLite database URL
        String url = "jdbc:sqlite:lib/QATable.db";

        // SQL query to check if the ID exists in the QuestionsAsked table
        String sql = "SELECT COUNT(*) AS count FROM QuestionsAsked WHERE ID = " + id;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return false;
    }

}
