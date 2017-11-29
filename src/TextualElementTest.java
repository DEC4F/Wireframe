import javafx.scene.text.TextAlignment;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextualElementTest {

    CannedText text1 = new CannedText(20,20,20,20, "Hello World");

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entity
     * FAILED: because I didn't implement a whole JavaFX GUI
     */
    @Test
    public void align() throws Exception {
        text1.align(TextAlignment.CENTER);
        assertEquals(text1.getAlignment(), TextAlignment.CENTER);
        text1.align(TextAlignment.JUSTIFY);
        assertEquals(text1.getAlignment(), TextAlignment.JUSTIFY);
        text1.align(TextAlignment.RIGHT);
        assertEquals(text1.getAlignment(), TextAlignment.RIGHT);
        text1.align(TextAlignment.LEFT);
        assertEquals(text1.getAlignment(), TextAlignment.LEFT);
    }

    /**
     * structural basis: first validation call failed
     * bad data: this textual element entity locked
     * error guessing: this textual element entity locked
     */
    @Test
    public void alignLocked() throws Exception {
        text1.setLocked(true);
        text1.align(TextAlignment.CENTER);
        assertTrue(text1.thrown);
    }

}