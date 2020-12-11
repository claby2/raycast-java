import java.awt.EventQueue;
import javax.swing.JFrame;
public class Main extends JFrame {
    private final int INITIAL_WIDTH = 640;
    private final int INITIAL_HEIGHT = 480;
    public Main() {
        add(new Panel());
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
