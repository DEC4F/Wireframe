import org.junit.Test;

import static org.junit.Assert.*;

public class AnnotationTest {

    Annotation annotation1 = new Annotation(10, 10, 10, 10, "Def last_project");
    Paragraph paragraph1 = new Paragraph(20, 20, 20, 20);

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entity
     * FAILED: because I didn't implement a whole JavaFX GUI
     */
    @Test
    public void testAnnotateNominal () throws Exception {
        annotation1.annotate(paragraph1);
        assertEquals(annotation1.getAnnotatedEntity(), paragraph1);
    }

    /**
     * structural basis: the validation call fails, either this annotation or the entity is locked, or the entity is an insatcne of annotation
     * bad data: this annotation locked
     * FAILED: because I didn't implement a whole JavaFX GUI
     */
    @Test
    public void testAnnotateLocked () throws Exception {
        annotation1.setLocked(true);
        annotation1.annotate(paragraph1);
        assertTrue(annotation1.isThrown());
    }

    /**
     * structural basis: assuming all conditions are true
     * good data: good wireframe entity
     * FAILED: because I didn't implement a whole JavaFX GUI
     */
    @Test
    public void testValidateAnnotatableNominal () throws Exception {
        Annotation.AnnotationTestHook.validateAnnotatable(paragraph1);
    }

    /**
     * structural basis: the second validation call fails, entity is locked
     * bad data: entity locked
     * FAILED: because I didn't implement a whole JavaFX GUI
     */
    @Test (expected = WireframeException.class)
    public void testValidateAnnotatableLockedEntity () throws Exception {
        paragraph1.setLocked(true);
        Annotation.AnnotationTestHook.validateAnnotatable(paragraph1);
    }

    /**
     * structural basis: the if statement fails, the entity to be annotated is also an annotation
     * bad data: entity is instance of annotation
     * FAILED: because I didn't implement a whole JavaFX GUI
     */
    @Test (expected = WireframeException.class)
    public void testValidateAnnotatableAnnotationEntity () throws Exception {
        Annotation.AnnotationTestHook.validateAnnotatable(annotation1);
    }

}