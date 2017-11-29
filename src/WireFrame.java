import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WireFrame extends JFrame{

    //////////////////////////////////
    // FIELD
    //////////////////////////////////

    // The list that tracks everything on the Wireframe currently, their indexes mark the order of layering
    static List<WireframeEntity> currentEntities = new ArrayList<>();

    //////////////////////////////////
    // MAIN
    //////////////////////////////////

    public static void main(String[] args) {
        ErrorHandler.setLog(new File("log/log.txt"));
        new WireFrame();
    }

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    private WireFrame() {
        SwingUtilities.invokeLater(() -> {
            launchGUI();
        });
    }

    //////////////////////////////////
    // HELPERS
    //////////////////////////////////

    /**
     * a GUI launcher, JFrame seems not a very good choice
     */
    private void launchGUI() {
        setTitle("WireFrame 1.0");
        setSize(1920, 1080);
        setLocation(0, 0);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * this method should update what's being displayed on GUI according any change in the currentEntities
     */
    private void updateGUI () {
        //TODO oh no, it's java GUI again
    }
}
