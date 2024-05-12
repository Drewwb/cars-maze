package src.Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class QuestionFactory {
    private Random random;


    public QuestionFactory() {
        random = new Random();
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
        return new MultipleChoiceQuestion(getIdFromDB("MultipleChoice"));
    }


    public Question createTrueFalseQuestion() {
        return new TrueFalseQuestion(getIdFromDB("TrueFalse"));
    }

    public Question createShortAnswerQuestion() {
        return new ShortAnswerQuestion(getIdFromDB("ShortAnswer"));
    }

    private int getIdFromDB(String type) {

        //////*****************
        // This initially searches the table for questions that have not yet been asked
        String sql = "SELECT ID FROM " + type;

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cars-maze/lib/QATable.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // List to store the IDs
            ArrayList<Integer> idList = new ArrayList<>();

            // Iterate through the result set and add IDs to the list
            while (rs.next()) {
                int id = rs.getInt("id");
                idList.add(id);
            }

            // Convert the ArrayList to an array
            Integer[] idArray = idList.toArray(new Integer[0]);

            // Go over each id and see if it has been asked
            // If it hasn't then return it
            for (int i = 0; i < idArray.length; i++) {
                if (!isIdInQuestionsAsked(idArray[i])) {
                    return idArray[i];
                }
            }

            //////***************
            // All questions from table have been asked so generate a random id from the table
            int randomIndex = random.nextInt(idArray.length);
            return idArray[randomIndex];

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isIdInQuestionsAsked(int id) {
        // SQLite database URL
        String url = "jdbc:sqlite:cars-maze/lib/QATable.db";

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
