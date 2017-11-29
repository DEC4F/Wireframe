import org.junit.Test;

import static org.junit.Assert.*;

public class ElementTest {

    private Paragraph paragraph1 = new Paragraph(20, 40, 1, 2);
    private Paragraph paragraph2 = new Paragraph(100, 100, 1, 1);
    private Box box3 = new Box(50, 40, 1, 1);

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entity
     */
    @Test
    public void testResizeNominal() throws Exception {
        assertEquals(paragraph1.getHeight(),1);
        assertEquals(paragraph1.getWidth(),2);
        paragraph1.resize(11,22);
        assertEquals(paragraph1.getHeight(),11);
        assertEquals(paragraph1.getWidth(),22);
    }

    /**
     * structural basis: first assertion fails
     * bad data: new height is negative
     * error guessing: input is negative
     */
    @Test (expected = AssertionError.class)
    public void testResizeNegHeight() throws Exception {
        assertEquals(paragraph1.getHeight(),1);
        assertEquals(paragraph1.getWidth(),2);
        paragraph1.resize(-11,22);
    }

    /**
     * structural basis: second assertion fails
     * bad data: new width is negative
     * error guessing: input is negative
     */
    @Test (expected = AssertionError.class)
    public void testResizeNegWidth() throws Exception {
        assertEquals(paragraph1.getHeight(),1);
        assertEquals(paragraph1.getWidth(),2);
        paragraph1.resize(11,-22);
    }

    /**
     * structural basis: validation call throws error
     * bad data: callee is locked
     * error guessing: callee is locked
     */
    @Test
    public void testResizeLocked() throws Exception {
        assertEquals(paragraph1.getHeight(),1);
        assertEquals(paragraph1.getWidth(),2);
        paragraph1.setLocked(true);
        paragraph1.resize(11,22);
        assertTrue(paragraph1.thrown);
    }

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entity
     */
    @Test
    public void testDeleteNominal() throws Exception {
        assertTrue(WireFrame.currentEntities.contains(paragraph1));
        paragraph1.delete();
        assertTrue(!WireFrame.currentEntities.contains(paragraph1));
    }

    /**
     * structural basis: validation call throws error
     * bad data: callee is locked
     * error guessing: callee is locked
     */
    @Test
    public void testDeleteLocked() throws Exception {
        assertTrue(WireFrame.currentEntities.contains(paragraph1));
        paragraph1.setLocked(true);
        paragraph1.delete();
        assertTrue(paragraph1.thrown);
    }

}