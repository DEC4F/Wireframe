/** Annotation class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
public class Annotation extends TextualElement {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    /** indicates whether this annotation is visible to user */
    private boolean isVisible = false;
    /** indicates which wireframe entity it's annotating on */
    private WireframeEntity annotatedEntity;

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate an annotation
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     * @param message     is the message of this annotation
     */
    public Annotation(int xCoordinate, int yCoordinate, int height, int width, String message) {
        super(xCoordinate, yCoordinate, height, width, message);
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /** add an annotation to a entity
     *
     * @param entity is the entity to be annotated
     * @throws WireframeException when the entity of the annotation is locked or the entity is also an annotation
     */
    public void annotate(WireframeEntity entity) {
        try {
            validateAnnotatable(entity);
            // create a new annotation on this entity
            annotatedEntity = entity;
            moveTo(entity.getXCoordinate(), entity.getYCoordinate());
        } catch (WireframeException e) {
            logError(e);
        }
    }

    //////////////////////////////////
    // ACCESSORS
    //////////////////////////////////

    /** check if is visible
     *
     * @return true if it's visible
     */
    boolean isVisible() {
        return isVisible;
    }

    /** get the entity being annotated on
     *
     * @return the entity
     */
    WireframeEntity getAnnotatedEntity() {
        return annotatedEntity;
    }

    /** change the visibility of the annotation
     *
     * @param visibility is the intended state of the visibility
     */
    public void setVisible(boolean visibility) {
        isVisible = visibility;
    }

    //////////////////////////////////
    // VALIDATION
    //////////////////////////////////

    /** verify both the entity and annotation is not locked, and the parameter entity cannot also be a annotation
     *
     * @param entity is the entity to be annotated
     * @throws WireframeException if the annotation is not modifiable or the entity is also a annotation
     */
    private void validateAnnotatable(WireframeEntity entity) throws WireframeException {
        validateModifiable();
        entity.validateModifiable();
        if (entity.getClass() == Annotation.class)
            throw new WireframeException(WireframeException.ErrorCode.CANNOT_ANNOTATE_AN_ANNOTATION);
    }

    //////////////////////////////////
    // TEST HOOK
    //////////////////////////////////

    /** test hook class for testing private method */
    static class AnnotationTestHook {

        /** private method to be tested */
        static void validateAnnotatable(WireframeEntity entity) throws WireframeException {
            Annotation annotation = new Annotation(0, 0, 0, 0, "Hello");
            annotation.validateAnnotatable(entity);
        }
    }

}