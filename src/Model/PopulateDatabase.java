package src.Model;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class PopulateDatabase {

    public static void main(String[] args) {
        String fileName = "lib/questions.txt";
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:lib/QATable.db");
        populateData(ds, fileName);
    }

    private static void clearDatabase(Connection connection) throws SQLException {
        String[] tables = {"MultipleChoice", "ShortAnswer", "TrueFalse", "QuestionsAsked"};
        for (String table : tables) {
            String sql = "DELETE FROM " + table;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        }
    }

    private static void populateData(SQLiteDataSource ds, String fileName) {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);


            createQuestionsAskedTable(connection);

            clearDatabase(connection);

            File file = new File(fileName);
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    // Skip any initial empty lines
                    String line = scanner.nextLine().trim();
                    while (line.isEmpty() && scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                    }

                    // Parse the ID
                    int id = Integer.parseInt(line);

                    // Read the question type
                    if (!scanner.hasNextLine()) break;
                    String type = scanner.nextLine().trim();

                    // Read the question
                    if (!scanner.hasNextLine()) break;
                    String question = scanner.nextLine().trim();

                    // Initialize variables
                    String options = "";
                    String answer;
                    String category;

                    // For 'MC' type questions, read the options
                    if ("MC".equals(type)) {
                        if (!scanner.hasNextLine()) break;
                        options = scanner.nextLine().trim();
                    }

                    // For all question types, read the answer
                    if (!scanner.hasNextLine()) break;
                    answer = scanner.nextLine().trim();

                    // Read the category
                    if (!scanner.hasNextLine()) break;
                    category = scanner.nextLine().trim();

                    // Insert the question into the database
                    insertQuestion(connection, type, id, question, options, answer, category);

                    // Skip the empty line after the entry if it exists
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                }
                connection.commit(); // Commit all changes at the end
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing ID: " + e.getMessage());
                connection.rollback(); // Rollback in case of an error
            } catch (SQLException e) {
                System.err.println("SQL exception: " + e.getMessage());
                connection.rollback(); // Rollback in case of an error
            }
        } catch (SQLException e) {
            System.err.println("SQL connection exception: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // Ensure connection is closed
                } catch (SQLException e) {
                    System.err.println("SQL connection close exception: " + e.getMessage());
                }
            }
        }
    }

    private static void createQuestionsAskedTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS QuestionsAsked (ID INTEGER PRIMARY KEY, Question TEXT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void insertQuestion(Connection connection, String type, int id, String question, String options, String answer, String category) throws SQLException {
        String tableName = getTableName(type);
        String sql = "";
        if ("MC".equals(type)) {
            sql = "INSERT INTO MultipleChoice (ID, QUESTION, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                String[] optionArray = options.split("-");
                statement.setInt(1, id);
                statement.setString(2, question);
                for (int i = 0; i < optionArray.length; i++) {
                    statement.setString(i + 3, optionArray[i].trim());
                }
                statement.setString(7, answer);
                statement.setString(8, category);
                statement.executeUpdate();
            }
        } else {
            sql = "INSERT INTO " + tableName + " (ID, QUESTION, ANSWER, CATEGORY) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.setString(2, question);
                statement.setString(3, answer);
                statement.setString(4, category);
                statement.executeUpdate();
            }
        }
    }

    private static String getTableName(String type) {
        switch (type) {
            case "MC":
                return "MultipleChoice";
            case "SAQ":
                return "ShortAnswer";
            case "TF":
                return "TrueFalse";
            default:
                throw new IllegalArgumentException("Invalid question type: " + type);
        }
    }
}