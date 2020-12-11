import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Player {
    private final double RADIUS;
    private Position position;
    private final double SPEED;

    private int dx;
    private int dy;

    public Player(double radius, double x, double y, double speed) {
        this.RADIUS = radius;
        this.position = new Position(x, y);
        this.SPEED = speed;
        this.dx = 0;
        this.dy = 0;
    }

    public void update() {
        move();
    }

    public void render(Graphics2D g2D) {
        g2D.setStroke(new BasicStroke(10));
        Ellipse2D ellipse2D = getEllipse();
        g2D.fill(ellipse2D);
        g2D.draw(ellipse2D);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                dy = -1;
                break;
            case KeyEvent.VK_S:
                dy = 1;
                break;
            case KeyEvent.VK_A:
                dx = -1;
                break;
            case KeyEvent.VK_D:
                dx = 1;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                dy = 0;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                dx = 0;
                break;
        }
    }

    private Ellipse2D getEllipse() {
        return new Ellipse2D.Double(position.x - RADIUS, position.y - RADIUS, RADIUS, RADIUS);
    }

    private void move() {
        this.position.x += dx * SPEED;
        this.position.y += dy * SPEED;
    }
}
