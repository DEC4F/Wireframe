import javafx.scene.text.TextAlignment;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextualElementTest {

    CannedText text1 = new CannedText(20,20,20,20, "Hello World");

    @Test
    public void align() throws Exception {
        text1.align(TextAlignment.CENTER);
        assertEquals(text1.getAlignment(), TextAlignment.CENTER);
    }

}