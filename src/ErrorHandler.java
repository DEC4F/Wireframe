import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

class ErrorHandler {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    private static final Logger LOGGER = Logger.getLogger(WireframeLogger.class.getName());

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    static void setLog(File errorLog) {
        try {
            System.setErr(new PrintStream(new FileOutputStream(errorLog)));
        } catch (IOException e) {
            System.setErr(System.err);
        }
    }

    static void logError(WireframeException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e.toString());
    }
}