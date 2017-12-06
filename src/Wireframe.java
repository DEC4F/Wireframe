import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** Canvas class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
public class Wireframe extends JFrame{

    //////////////////////////////////
    // FIELD
    //////////////////////////////////

    /** The list that tracks everything on the Wireframe currently, their indexes mark the order of layering */
    static List<WireframeEntity> currentEntities = new ArrayList<>();
    /** The list of annotations that is separate from everything else */
    static List<Annotation> annotations = new ArrayList<>();

    //////////////////////////////////
    // MAIN
    //////////////////////////////////

    /** main method that runs the program */
    public static void main(String[] args) {
        ErrorHandler.setLog(new File("log/log.txt"));
        new Wireframe();
    }

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** initialize the canvas and launch the GUI */
    private Wireframe() {
        SwingUtilities.invokeLater(() -> {
            launchGUI();
        });
    }

    //////////////////////////////////
    // HELPERS
    //////////////////////////////////

    /** a GUI launcher, JFrame seems not a very good choice */
    private void launchGUI() {
        setTitle("Wireframe 1.0");
        setSize(1920, 1080);
        setLocation(0, 0);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /** this method should update what's being displayed on GUI according any change in the currentEntities */
    private void updateGUI () {
        //TODO complete the Java GUI
    }
}
