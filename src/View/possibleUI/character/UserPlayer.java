package src.View.possibleUI.character;

import static src.View.possibleUI.main.GamePanel.TILE_SIZE;

import src.View.possibleUI.main.KeyInput;
import src.View.possibleUI.main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents the user-controlled player in the game.
 * Extends the Character class to manage the player's state and behavior.
 */
public class UserPlayer extends Character {
    // Default starting coordinates and speed for the player.
    private static final int DEFAULT_X = 100;
    private static final int DEFAULT_Y = 100;
    private static final int DEFAULT_SPEED = 3;

    // References to the game panel and key input handler.
    private GamePanel gamePanel;
    private KeyInput input;

    /**
     * Constructs a new UserPlayer object.
     *
     * @param gamePanel The game panel on which the character is drawn and interacts.
     * @param input The KeyInput object for handling user input.
     */
    public UserPlayer(GamePanel gamePanel, KeyInput input) {
        this.gamePanel = gamePanel;
        this.input = input;
        setDefaultValues();
    }

    /**
     * Initializes player's default properties.
     */
    private void setDefaultValues() {
        x = DEFAULT_X;
        y = DEFAULT_Y;
        speed = DEFAULT_SPEED;
    }

    /**
     * Updates the player's position based on user input.
     * This method is called once per frame.
     */
    @Override
    public void update() {
        if (input.upPressed) {
            move(Direction.UP);
        }
        if (input.downPressed) {
            move(Direction.DOWN);
        }
        if (input.leftPressed) {
            move(Direction.LEFT);
        }
        if (input.rightPressed) {
            move(Direction.RIGHT);
        }
    }

    /**
     * Moves the player in a specified direction.
     *
     * @param direction The direction to move the player.
     */
    private void move(Direction direction) {
        switch (direction) {
            case UP:
                y -= speed; // Move up by decreasing the y-coordinate.
                break;
            case DOWN:
                y += speed; // Move down by increasing the y-coordinate.
                break;
            case LEFT:
                x -= speed; // Move left by decreasing the x-coordinate.
                break;
            case RIGHT:
                x += speed; // Move right by increasing the x-coordinate.
                break;
        }
    }

    /**
     * Draws the player on the game panel.
     *
     * @param g2d The graphics context used for drawing.
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE); // Set the color for the player.
        g2d.fillRect(x, y, TILE_SIZE, TILE_SIZE); // Draw the player as a rectangle.
    }

    /**
     * Enum to define possible movement directions.
     */
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
