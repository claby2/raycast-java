import java.awt.*;
import java.awt.geom.*;

public class Wall {
    private Position startPosition;
    private Position endPosition;

    public Wall(double x1, double y1, double x2, double y2) {
        startPosition = new Position();
        endPosition = new Position();
        startPosition.x = x1;
        startPosition.y = y1;
        endPosition.x = x2;
        endPosition.y = y2;
    }

    public Wall(Position startPosition, Position endPosition) {
        this(startPosition.x, startPosition.y, endPosition.x, endPosition.y);
    }

    public void render(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.setStroke(new BasicStroke(3));
        g2D.draw(new Line2D.Double(startPosition.x, startPosition.y, endPosition.x, endPosition.y));
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }
}
