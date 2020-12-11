import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Player {
    private final double RADIUS;
    private Position position;
    private final double SPEED;

    public Player(double radius, double x, double y, double speed) {
        this.RADIUS = radius;
        this.position = new Position(x, y);
        this.SPEED = speed;
    }

    public void update(Position mousePosition) {
        setXY(mousePosition);
    }

    public void render(Graphics2D g2D) {
        g2D.setStroke(new BasicStroke(10));
        Ellipse2D ellipse2D = getEllipse();
        g2D.fill(ellipse2D);
        g2D.draw(ellipse2D);
    }

    public Position getPosition() {
        return position;
    }

    private Ellipse2D getEllipse() {
        return new Ellipse2D.Double(position.x - RADIUS, position.y - RADIUS, RADIUS, RADIUS);
    }

    private void setXY(Position position) {
        setXY(position.x, position.y);
    }

    private void setXY(double x, double y) {
        position.x = x;
        position.y = y;
    }
}
