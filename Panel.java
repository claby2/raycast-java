import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {
    private Timer timer;
    private final int DELAY = 10;
    private Dimension dimension;

    private Player player;
    private WallManager wallManager;
    private RayRenderer rayRenderer;

    public Panel() {
        dimension = new Dimension();
        addKeyListener(new CustomKeyAdapter());
        setBackground(Color.black);
        setFocusable(true);

        player = new Player(30.0, 100.0, 100.0, 5.0);
        wallManager = new WallManager(10, dimension.width, dimension.height);
        rayRenderer = new RayRenderer();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }

    private void step() {
        dimension = getSize();
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        Position mouseScreenPosition = new Position(
            mousePoint.x - getLocationOnScreen().x, mousePoint.y - getLocationOnScreen().y);
        player.update(mouseScreenPosition);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        player.render(g2D);
        rayRenderer.renderRays(g2D, player, wallManager.getWalls());
        wallManager.renderWalls(g2D);
    }

    private class CustomKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            wallManager.keyPressed(e, dimension.width, dimension.height);
        }
    }
}
