package src.Model.Questions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question {
    private String type;
    private int id;
    private String question;
    private String answer;
    private String category;
    /*
    Path for QATable.db, it should be in the lib folder
    Drew: ""
    Huy: "cars-maze/"
    Jafar: "cars-maze/"
     */
    private final String PATH = "cars-maze/"; //Put the correct string here from above

    private String lineSeparator = System.lineSeparator();


    public Question(String type, int id) {
        setType(type);
        setId(id);
        setQuestionAndAnswer(id, type); // Change to set question and answer based on type
        setCategory(id);
    }

    public void setType(String type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    public void setId(int id) {
        if(id > 0 && id < 11) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    // Concrete method
    public boolean isCorrectAnswer(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer.trim());
    }

    // Method to set question and answer based on type
    private void setQuestionAndAnswer(int id, String type) {
        String question = null;
        String answer = null;
        String tableName = null;

        // Determine the table name based on the question type
        switch (type) {
            case "MultipleChoice":
                tableName = "MultipleChoice";
                break;
            case "TrueFalse":
                tableName = "TrueFalse";
                break;
            case "ShortAnswer":
                tableName = "ShortAnswer";
                break;
            default:
                throw new IllegalArgumentException("Invalid question type");
        }

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH + "lib/QATable.db");
             PreparedStatement stmt = conn.prepareStatement("SELECT QUESTION, ANSWER FROM " + tableName + " WHERE ID = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                question = rs.getString("QUESTION");
                answer = rs.getString("ANSWER");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.question = question;
        this.answer = answer;
    }

    private void setCategory(int id) {
        String category = null;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH + "lib/QATable.db");
             PreparedStatement stmt = conn.prepareStatement("SELECT CATEGORY FROM MultipleChoice WHERE ID = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                category = rs.getString("CATEGORY");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.category = category;
    }

    @Override
    public String toString(){      //toString method tells us the state of the question object and other details.
        StringBuilder myQuestionDetails = new StringBuilder();
        myQuestionDetails.append("Question: " + question + lineSeparator);
        myQuestionDetails.append("Answer: " + answer + lineSeparator);
        myQuestionDetails.append("ID: " + id + lineSeparator);
        myQuestionDetails.append("Type: " + type + lineSeparator);
        myQuestionDetails.append("Category: " + category + lineSeparator);
        return myQuestionDetails.toString();
    }
}
