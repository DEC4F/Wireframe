import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GroupTest {

    private Group group = new Group(0, 0, 0, 0);
    private Paragraph paragraph1 = new Paragraph(20, 20, 20, 20);
    private Headline headline1 = new Headline(40, 40, 40, 40);
    private ComboBox comboBox1 = new ComboBox(10,10,10,10);
    private ScrollBar scrollBar1 = new ScrollBar(7,7,7,7);

    /**
     * structural basis: assuming all condition is true
     * good data: 3 wireframe entities
     */
    @Test
    public void testGroupNominal() throws Exception {
        List<? extends WireframeEntity> entities = Arrays.asList(paragraph1, headline1, scrollBar1);
        group.group(entities);

        assertEquals(group.getEntityList().size(), entities.size());
        assertEquals(group.getXCoordinate(), 7);
        assertEquals(group.getYCoordinate(), 40);
        assertEquals(group.getHeight(), 40);
        assertEquals(group.getWidth(), 73);

        for (int i = 0; i < entities.size(); i++)
            assertEquals(group.getEntityList().get(i), entities.get(i));
    }

    /**
     * structural basis: assertion fails & condition of first for loop fails
     * boundary analysis: list is empty
     * bad data: too few data
     * error guessing: empty list
     */
    @Test (expected = AssertionError.class)
    public void testGroupEmptyList() throws Exception {
        List<? extends WireframeEntity> entities = Collections.emptyList();
        group.group(entities);
    }

    /**
     * structural basis: assertion fails
     * bad data: too few data
     * error guessing: null input
     */
    @Test (expected = AssertionError.class)
    public void testGroupNullInput() throws Exception {
        group.group(null);
    }

    /**
     * structural basis: assertion fails
     * bad data: too few data
     * error guessing: entity already in group
     */
    @Test
    public void testGroupLockedEntity() throws Exception {
        comboBox1.setLocked(true);
        List<? extends WireframeEntity> entities = Arrays.asList(comboBox1);
        group.group(entities);
        assertTrue(group.thrown);
    }

    /**
     * structural basis: assertion fails
     * bad data: too few data
     * error guessing: entity already in group
     */
    @Test
    public void testGroupAlreadyInGroup() throws Exception {
        List<? extends WireframeEntity> entities = Arrays.asList(paragraph1, paragraph1, headline1, scrollBar1);
        group.group(entities);
        assertTrue(group.thrown);
    }

    /**
     * structural basis: assuming all condition is true
     * good data: 3 wireframe entities
     */
    @Test
    public void testDeleteNominal() throws Exception {
        List<? extends WireframeEntity> entities = Arrays.asList(paragraph1, headline1, scrollBar1);
        group.group(entities);
        assertTrue(WireFrame.currentEntities.contains(group));
        assertTrue(WireFrame.currentEntities.contains(paragraph1));
        assertTrue(WireFrame.currentEntities.contains(headline1));
        assertTrue(WireFrame.currentEntities.contains(scrollBar1));
        assertTrue(group.getEntityList().contains(paragraph1));
        assertTrue(group.getEntityList().contains(headline1));
        assertTrue(group.getEntityList().contains(scrollBar1));

        group.delete();
        assertTrue(!WireFrame.currentEntities.contains(group));
        assertTrue(!WireFrame.currentEntities.contains(paragraph1));
        assertTrue(!WireFrame.currentEntities.contains(headline1));
        assertTrue(!WireFrame.currentEntities.contains(scrollBar1));
        assertTrue(!group.getEntityList().contains(paragraph1));
        assertTrue(!group.getEntityList().contains(headline1));
        assertTrue(!group.getEntityList().contains(scrollBar1));
    }

    /**
     * structural basis: condition of first for loop fails
     * boundary analysis: list is empty
     * bad data: too few data
     */
    @Test
    public void testDeleteEmptyList() throws Exception {
        group.delete();
        assertTrue(!WireFrame.currentEntities.contains(group));
    }

    /**
     * structural basis: condition of first if statement fails
     * bad data: data is not locked in a group
     */
    @Test
    public void testDeleteNotLockedMember() throws Exception {
        List<? extends WireframeEntity> entities = Arrays.asList(comboBox1);
        // entity would be locked during group()
        group.group(entities);
        // manually unlock a member
        comboBox1.setLocked(false);
        // do delete
        group.delete();
        assertTrue(group.thrown);
    }

    /**
     * structural basis: assuming all condition is true
     * good data: good wireframe entity
     */
    @Test
    public void testValidateGroupingNominal () throws Exception {
        Group.GroupTestHook.validateGrouping(comboBox1);
    }

    /**
     * structural basis: second method call throw exception -- group is locked
     * bad data: locked wireframe entity
     * error guessing: entity locked
     */
    @Test (expected = WireframeException.class)
    public void testValidateEntityLocked () throws Exception {
        comboBox1.setLocked(true);
        Group.GroupTestHook.validateGrouping(comboBox1);
    }

    /**
     * structural basis: the if statement fails -- already in group
     * bad data: wireframe entity already in group
     * error guessing: entity already in group
     */
    @Test (expected = WireframeException.class)
    public void testValidateGroupingLastTest () throws Exception {
        Group.GroupTestHook.validateGroupingLastTest(comboBox1);
    }

}