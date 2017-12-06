import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Error handler class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
class ErrorHandler {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    /** the logger that logs special event like throwing exception */
    private static final Logger LOGGER = Logger.getLogger(WireframeLogger.class.getName());

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /** set up the log */
    static void setLog(File errorLog) {
        try {
            System.setErr(new PrintStream(new FileOutputStream(errorLog)));
        } catch (IOException e) {
            System.setErr(System.err);
        }
    }

    /** logs a specific exception */
    static void logError(WireframeException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e.toString());
    }
}