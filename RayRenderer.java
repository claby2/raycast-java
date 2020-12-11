import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
import java.util.ArrayList;

public class RayRenderer {
    private final double RAY_DENSITY;
    private final double STEP;
    private final double MAXIMUM_FACTOR;
    private final double TWO_PI;

    public RayRenderer() {
        RAY_DENSITY = 50.0;
        STEP = (1.0 / RAY_DENSITY);
        // MAXIMUM_FACTOR is an arbitrary large value
        MAXIMUM_FACTOR = 10000;
        TWO_PI = 2 * Math.PI;
    }

    public void renderRays(Graphics2D g2D, Player player, ArrayList<Wall> walls) {
        Position playerPosition = player.getPosition();
        for (double i = 0; i < TWO_PI; i += STEP) {
            Position end = new Position(Math.cos(i) * MAXIMUM_FACTOR, Math.sin(i) * MAXIMUM_FACTOR);
            for (Wall wall : walls) {
                Position wallStartPosition = wall.getStartPosition();
                Position wallEndPosition = wall.getEndPosition();
                final double DENOMINATOR = Double.valueOf(
                    ((playerPosition.x - end.x) * (wallStartPosition.y - wallEndPosition.y))
                    - ((playerPosition.y - end.y) * (wallStartPosition.x - wallEndPosition.x)));
                if (DENOMINATOR == 0) {
                    // Lines are parallel or coincident
                    continue;
                }
                final double t = Double.valueOf(((playerPosition.x - wallStartPosition.x)
                                                        * (wallStartPosition.y - wallEndPosition.y)
                                                    - (playerPosition.y - wallStartPosition.y)
                                                        * (wallStartPosition.x - wallEndPosition.x))
                    / DENOMINATOR);
                final double u = -1
                    * Double.valueOf(
                        ((playerPosition.x - end.x) * (playerPosition.y - wallStartPosition.y)
                            - (playerPosition.y - end.y) * (playerPosition.x - wallStartPosition.x))
                        / DENOMINATOR);
                if (t > 0 && t < 1 && u > 0 && u < 1) {
                    // Lines intersect
                    end.x = playerPosition.x + t * (end.x - playerPosition.x);
                    end.y = playerPosition.y + t * (end.y - playerPosition.y);
                }
            }
            renderRay(g2D, playerPosition, end);
        }
    }

    private void renderRay(Graphics2D g2D, Position start, Position end) {
        g2D.setColor(Color.GRAY);
        g2D.setStroke(new BasicStroke(1));
        g2D.draw(new Line2D.Double(start.x, start.y, end.x, end.y));
    }
}
