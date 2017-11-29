
public class Annotation extends TextualElement {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    private boolean isVisible = false;
    private WireframeEntity annotatedEntity;

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    Annotation(int xCoordinate, int yCoordinate, int height, int width, String message) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.textMsg.setText(message);

        WireFrame.currentEntities.add(this);
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /**
     * add an annotation on a entity
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

    boolean isVisible() {
        return isVisible;
    }

    WireframeEntity getAnnotatedEntity() {
        return annotatedEntity;
    }

    public void setVisible(boolean visibility) {
        isVisible = visibility;
    }

    //////////////////////////////////
    // VALIDATION
    //////////////////////////////////

    /**
     * verify both the entity and annotation is not locked, and the parameter entity cannot also be a annotation
     *
     * @param entity is the entity to be annotated
     */
    private void validateAnnotatable(WireframeEntity entity) throws WireframeException {
        validateModifiable();
        entity.validateModifiable();
        if (entity.getClass() == Annotation.class)
            throw new WireframeException(WireframeException.ErrorCode.CANNOT_ANNOTATE_AN_ANNOTATION);
    }

    static class AnnotationTestHook {
        static void validateAnnotatable(WireframeEntity entity) throws WireframeException {
            Annotation annotation = new Annotation(0,0,0,0,"Hello");
            annotation.validateAnnotatable(entity);
        }
    }

}
