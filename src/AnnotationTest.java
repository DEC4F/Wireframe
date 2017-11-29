import org.junit.Test;

import static org.junit.Assert.*;

public class AnnotationTest {

    Annotation annotation1 = new Annotation(10, 10, 10, 10, "Def last_project");
    Paragraph paragraph1 = new Paragraph(20, 20, 20, 20);

    @Test
    public void testAnnotateNominal () throws Exception {
        annotation1.annotate(paragraph1);
        assertEquals(annotation1.getAnnotatedEntity(), paragraph1);
    }

    @Test
    public void testValidateAnnotatableNominal () throws Exception {
        Annotation.AnnotationTestHook.validateAnnotatable(paragraph1);
    }

}