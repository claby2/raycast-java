import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class WallManager {
    private Random random;
    private ArrayList<Wall> walls;
    private final int numberOfWalls;
    private int maximumWidth;
    private int maximumHeight;

    public WallManager(int numberOfWalls, int maximumWidth, int maximumHeight) {
        random = new Random();
        this.numberOfWalls = numberOfWalls;
        walls = new ArrayList<Wall>();
        instantiateWalls();
        this.maximumWidth = maximumWidth;
        this.maximumHeight = maximumHeight;
    }

    public void renderWalls(Graphics2D g2D) {
        for (Wall wall : walls) {
            wall.render(g2D);
        }
    }

    public void keyPressed(KeyEvent e, int maximumWidth, int maximumHeight) {
        this.maximumWidth = maximumWidth;
        this.maximumHeight = maximumHeight;
        if (e.getKeyCode() == KeyEvent.VK_R) {
            walls.clear();
            instantiateWalls();
        }
    }

    private void instantiateWalls() {
        for (int i = 0; i < numberOfWalls; ++i) {
            walls.add(getRandomWall());
        }
    }

    private Wall getRandomWall() {
        Wall wall = new Wall(getRandomPosition(), getRandomPosition());
        return wall;
    }

    private Position getRandomPosition() {
        return new Position(
            random.nextInt(Math.max(maximumWidth, 1)), random.nextInt(Math.max(maximumHeight, 1)));
    }
}
