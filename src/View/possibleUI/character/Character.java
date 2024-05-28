package src.View.possibleUI.character;

import java.awt.Graphics2D;

public abstract class Character {
    public int x, y;
    public int speed;

    public abstract void update();

    public abstract void draw(Graphics2D g2);
}
