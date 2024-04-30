package src.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MultipleChoiceQuestion extends Question {

    String[] options = new String[4];

    public MultipleChoiceQuestion(int id) {
        super("MultipleChoice", id);
        setOptions(id);
    }

    private void setOptions(int id) {
        String[] allOptions = new String[4];
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lib/QATable.db");
             PreparedStatement stmt = conn.prepareStatement("SELECT OPTION1, OPTION2, OPTION3, OPTION4 FROM MultipleChoice WHERE ID = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                allOptions[0] = rs.getString("OPTION1");
                allOptions[1] = rs.getString("OPTION2");
                allOptions[2] = rs.getString("OPTION3");
                allOptions[3] = rs.getString("OPTION4");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.options = allOptions;
    }

    public String[] getOptions() {
        return options;
    }



}
