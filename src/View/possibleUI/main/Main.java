package src.View.possibleUI.main;
import javax.swing.JFrame;

/**
 * The Main class serves as the entry point for the application.
 * It sets up the main window and starts the game.
 */
public class Main {
    public static void main(String[] args) {
        // Create a new JFrame to serve as the main window for the application.
        JFrame window = new JFrame();

        // Configure the JFrame to exit the application when the window is closed.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Disable resizing of the window to maintain a fixed display area.
        window.setResizable(false);

        // Set the title of the window to "Trivia Maze" to reflect the game's theme.
        window.setTitle("Trivia Maze");

        // Create the main game panel where the game will be drawn and managed.
        GamePanel gamePanel = new GamePanel();

        // Add the game panel to the window, which allows it to display content.
        window.add(gamePanel);

        // Adjust the window size to fit the preferred size and layouts of its subcomponents.
        window.pack();

        // Center the window on the screen to improve accessibility and usability.
        window.setLocationRelativeTo(null);

        // Make the window visible, showing the game panel and allowing interaction.
        window.setVisible(true);

        // Start the main game thread where game logic is executed continuously.
        gamePanel.startGameThread();
    }
}
