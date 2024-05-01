package src.View.possibleUI.main;

import src.View.possibleUI.character.UserPlayer;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Represents the main panel in the game where all the drawing and game updates occur.
 * It uses a JPanel as the base class for graphics drawing.
 */
public class GamePanel extends JPanel implements Runnable {
    public static final int TILE_SIZE = 16;  // Base tile size in pixels.
    private static final int SCALE = 4;      // Scaling factor for the tiles.
    private static final int FINAL_TILE_SIZE = TILE_SIZE * SCALE;  // Final size of tiles after scaling.
    private static final int MAX_SCREEN_COL = 16;  // Number of tile columns in the game screen.
    private static final int MAX_SCREEN_ROW = 16;  // Number of tile rows in the game screen.
    private static final int SCREEN_WIDTH = FINAL_TILE_SIZE * MAX_SCREEN_COL;  // Width of the game screen.
    private static final int SCREEN_HEIGHT = FINAL_TILE_SIZE * MAX_SCREEN_ROW;  // Height of the game screen.
    private static final int FPS = 60;  // Frames per second, setting the game's refresh rate.

    private final KeyInput keyInput = new KeyInput();  // Key input handler for managing keyboard inputs.
    private Thread gameThread;  // Thread on which the game loop runs.
    private UserPlayer player = new UserPlayer(this, keyInput);  // The player controlled by the user.

    /**
     * Constructs a GamePanel setting up the necessary configurations.
     */
    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));  // Set the size of the JPanel.
        setBackground(Color.BLACK);  // Set the background color of the JPanel.
        setDoubleBuffered(true);  // Enable double buffering to reduce flickering.
        addKeyListener(keyInput);  // Add the key listener to the JPanel to receive key events.
        setFocusable(true);  // Allow the JPanel to gain focus to receive key inputs.
    }

    /**
     * Starts the game thread where the game loop will run.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The game loop: updates the game state, redraws the screen, and synchronizes the timing.
     */
    @Override
    public void run() {
        double interval = 1000000000.0 / FPS;  // Calculate the time each frame should take in nanoseconds.
        double nextInterval = System.nanoTime() + interval;

        while (gameThread != null) {
            update();  // Update the game state
            repaint();  // Redraw the game panel.

            try {
                double remainingTime = (nextInterval - System.nanoTime()) / 1000000;  // Calculate remaining time in milliseconds for the sleep.
                if (remainingTime < 0) {
                    remainingTime = 0;  // Prevent negative sleep time
                }
                Thread.sleep((long) remainingTime);  // Synchronize the frame rate
                nextInterval += interval;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Handle thread interruption properly.
            }
        }
    }

    /**
     * Updates the state of the game objects
     */
    private void update() {
        player.update();  // Update player's state.
    }

    /**
     * Custom painting of all game elements
     *
     * @param g The Graphics object provided by JPanel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);  // Draw the player.
        g2d.dispose();  // Clean up the graphics context to avoid resource leaks
    }
}