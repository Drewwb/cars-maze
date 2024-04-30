package src.Model;

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

    public Question(String type, int id) {
        this.type = type;
        this.id = id;
        setQuestion(id);
        setAnswer(id);
        setCategory(id);
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

    //gets question based on id and returns it
    private void setQuestion(int id) {
        String question = null;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lib/QATable.db");
             PreparedStatement stmt = conn.prepareStatement("SELECT QUESTION FROM MultipleChoice WHERE ID = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                question = rs.getString("QUESTION");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.question = question;
    }

    //gets answer based on id and returns it
    private void setAnswer(int id) {
        String answer = null;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lib/QATable.db");
             PreparedStatement stmt = conn.prepareStatement("SELECT ANSWER FROM MultipleChoice WHERE ID = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                answer = rs.getString("ANSWER");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.answer = answer;
    }

    private void setCategory(int id) {
        String category = null;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lib/QATable.db");
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
}