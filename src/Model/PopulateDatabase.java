package src.Model;
//java.sql docs
//https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PopulateDatabase {
    public static void main(String[] args) {
        String fileName = "questions.txt"; // Name of your text file
        String url = "jdbc:sqlite:QATable.db"; // Adjust path to your SQLite database file

        try (Connection connection = DriverManager.getConnection(url)) {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String type = scanner.nextLine().trim();
                String question = scanner.nextLine().trim();
                String options = "";
                String answer = scanner.nextLine().trim();
                String category = scanner.nextLine().trim();

                if (type.equals("MC")) {
                    options = answer;
                    answer = scanner.nextLine().trim();
                }

                switch (type) {
                    case "MC":
                        insertMultipleChoiceQuestion(connection, question, options, answer, category);
                        break;
                    case "SAQ":
                        insertShortAnswerQuestion(connection, question, answer, category);
                        break;
                    case "TF":
                        insertTrueFalseQuestion(connection, question, answer, category);
                        break;
                }
            }
            scanner.close();
            System.out.println("Data inserted successfully.");
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertMultipleChoiceQuestion(Connection connection, String question, String options, String answer, String category) throws SQLException {
        String sql = "INSERT INTO MultipleChoice (QUESTION, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String[] optionArray = options.split("-");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question);
            for (int i = 0; i < optionArray.length; i++) {
                statement.setString(i + 2, optionArray[i]);
            }
            statement.setString(6, answer);
            statement.setString(7, category);
            statement.executeUpdate();
        }
    }

    private static void insertShortAnswerQuestion(Connection connection, String question, String answer, String category) throws SQLException {
        String sql = "INSERT INTO ShortAnswer (QUESTION, ANSWER, CATEGORY) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question);
            statement.setString(2, answer);
            statement.setString(3, category);
            statement.executeUpdate();
        }
    }

    private static void insertTrueFalseQuestion(Connection connection, String question, String answer, String category) throws SQLException {
        String sql = "INSERT INTO TrueFalse (QUESTION, ANSWER, CATEGORY) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question);
            statement.setString(2, answer);
            statement.setString(3, category);
            statement.executeUpdate();
        }
    }
}
