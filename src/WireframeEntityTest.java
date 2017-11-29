import org.junit.Test;

import static org.junit.Assert.*;

public class WireframeEntityTest {

    private Paragraph paragraph1 = new Paragraph(20, 40, 1, 1);
    private Paragraph paragraph2 = new Paragraph(100, 100, 1, 1);
    private Box box3 = new Box(50, 40, 1, 1);

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entity
     */
    @Test
    public void testMoveToNominal() throws Exception {
        assertEquals(paragraph1.getXCoordinate(), 20);
        assertEquals(paragraph1.getYCoordinate(), 40);
        paragraph1.moveTo(66, 77);
        assertEquals(paragraph1.getXCoordinate(), 66);
        assertEquals(paragraph1.getYCoordinate(), 77);
    }

    /**
     * structural basis: first assertion fails
     * bad data: new X is negative
     * error guessing: input is negative
     */
    @Test (expected = AssertionError.class)
    public void testMoveToNegXPos() throws Exception {
        paragraph1.moveTo(-1, 77);
    }

    /**
     * structural basis: second assertion fails
     * bad data: new Y is negative
     * error guessing: input is negative
     */
    @Test (expected = AssertionError.class)
    public void testMoveToNegYPos() throws Exception {
        paragraph1.moveTo(66, -100);
    }

    /**
     * structural basis: entity is locked
     * bad data: entity is locked
     * error guessing: entity is locked
     */
    @Test
    public void testMoveToLocked() throws Exception {
        paragraph1.setLocked(true);
        paragraph1.moveTo(66, 77);
        assertTrue(paragraph1.thrown);
    }

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entities
     */
    @Test
    public void testBringToFrontNominal() throws Exception {
        assertEquals(WireFrame.currentEntities.get(0), paragraph1);
        assertEquals(WireFrame.currentEntities.get(1), paragraph2);
        assertEquals(WireFrame.currentEntities.get(2), box3);
        box3.bringToFront();
        assertEquals(WireFrame.currentEntities.get(0), box3);
        assertEquals(WireFrame.currentEntities.get(1), paragraph1);
        assertEquals(WireFrame.currentEntities.get(2), paragraph2);
        paragraph2.bringToFront();
        assertEquals(WireFrame.currentEntities.get(0), paragraph2);
        assertEquals(WireFrame.currentEntities.get(1), box3);
        assertEquals(WireFrame.currentEntities.get(2), paragraph1);
    }

    /**
     * structural basis: assertion fails
     * boundary test: not in list
     * bad data: entity deleted from list
     * error guessing: entity deleted from list
     */
    @Test (expected = AssertionError.class)
    public void testBringToFrontNotInList() throws Exception {
        assertEquals(WireFrame.currentEntities.get(0), paragraph1);
        assertEquals(WireFrame.currentEntities.get(1), paragraph2);
        assertEquals(WireFrame.currentEntities.get(2), box3);
        box3.delete();
        box3.bringToFront();
    }

    /**
     * structural basis: callee entity is locked
     * bad data: entity locked
     */
    @Test
    public void testBringToFrontLocked() throws Exception {
        assertEquals(WireFrame.currentEntities.get(0), paragraph1);
        assertEquals(WireFrame.currentEntities.get(1), paragraph2);
        assertEquals(WireFrame.currentEntities.get(2), box3);
        box3.setLocked(true);
        box3.bringToFront();
        assertTrue(box3.thrown);
    }

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entities
     */
    @Test
    public void testSwapWithNominal() throws Exception {
        assertEquals(WireFrame.currentEntities.get(0), paragraph1);
        assertEquals(WireFrame.currentEntities.get(1), paragraph2);
        assertEquals(WireFrame.currentEntities.get(2), box3);
        box3.swapWith(paragraph1);
        assertEquals(WireFrame.currentEntities.get(0), box3);
        assertEquals(WireFrame.currentEntities.get(1), paragraph2);
        assertEquals(WireFrame.currentEntities.get(2), paragraph1);
        paragraph2.swapWith(paragraph1);
        assertEquals(WireFrame.currentEntities.get(0), box3);
        assertEquals(WireFrame.currentEntities.get(1), paragraph1);
        assertEquals(WireFrame.currentEntities.get(2), paragraph2);
    }

    /**
     * structural basis: assertion fails
     * boundary test: not in list
     * bad data: this entity deleted from list
     * error guessing: this entity deleted from list
     */
    @Test (expected = AssertionError.class)
    public void testSwapWithThisNotInList() throws Exception {
        assertEquals(WireFrame.currentEntities.get(0), paragraph1);
        assertEquals(WireFrame.currentEntities.get(1), paragraph2);
        paragraph1.delete();
        paragraph1.swapWith(paragraph2);
    }

    /**
     * structural basis: assertion fails
     * boundary test: not in list
     * bad data: argument entity deleted from list
     * error guessing: argument entity deleted from list
     */
    @Test (expected = AssertionError.class)
    public void testSwapWithArgNotInList() throws Exception {
        assertEquals(WireFrame.currentEntities.get(0), paragraph1);
        assertEquals(WireFrame.currentEntities.get(1), paragraph2);
        paragraph2.delete();
        paragraph1.swapWith(paragraph2);
    }

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entities
     */
    @Test
    public void testValidateModifiableNominal() throws Exception {
        paragraph1.validateModifiable();
    }

    /**
     * structural basis: assertion fails
     * boundary test: not in list
     * bad data: this entity deleted from list
     * error guessing: this entity deleted from list
     */
    @Test (expected = AssertionError.class)
    public void testValidateModifiableDeleted() throws Exception {
        paragraph1.delete();
        paragraph1.validateModifiable();
    }

    /**
     * structural basis: if statement fails
     * bad data: entity locked
     * error guessing: entity locked
     */
    @Test (expected = WireframeException.class)
    public void testValidateModifiableLocked() throws Exception {
        paragraph1.setLocked(true);
        paragraph1.validateModifiable();
    }

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entities
     */
    @Test
    public void testValidateSwappableNominal() throws Exception {
        paragraph1.validateSwappable(paragraph2);
    }

    /**
     * structural basis: first validation call failed
     * bad data: this entity locked
     * error guessing: this entity locked
     */
    @Test (expected = WireframeException.class)
    public void testValidateSwappableThisLocked() throws Exception {
        paragraph1.setLocked(true);
        paragraph1.validateSwappable(paragraph2);
    }

    /**
     * structural basis: second validation call failed
     * bad data: arg entity locked
     * error guessing: arg entity locked
     */
    @Test (expected = WireframeException.class)
    public void testValidateSwappableArgLocked() throws Exception {
        paragraph2.setLocked(true);
        paragraph1.validateSwappable(paragraph2);
    }

}