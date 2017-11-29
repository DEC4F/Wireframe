import java.util.Collections;

abstract class WireframeEntity {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    int xCoordinate;
    int yCoordinate;
    int height;
    int width;
    boolean isLocked = false;
    // debug aid for testing if try-catch is functioning
    static boolean thrown = false;

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    public void moveTo(int newX, int newY) {
        assert newX >= 0;
        assert newY >= 0;

        try {
            validateModifiable();
        } catch (WireframeException e) {
            logError(e);
        }
        setXCoordinate(newX);
        setYCoordinate(newY);
    }

    /**
     * changes the index of this entity in the currentEntities to 0
     *
     * @throws WireframeException when this entity is locked or not in the list
     */
    public void bringToFront() throws WireframeException {
        assert WireFrame.currentEntities.contains(this);

        try {
            validateModifiable();
        } catch (WireframeException e) {
            logError(e);
        }
        // remove this entity from old pos and then add to head of the list
        WireFrame.currentEntities.remove(WireFrame.currentEntities.indexOf(this));
        WireFrame.currentEntities.add(0, this);
    }

    /**
     * swaps the position of this and parameter entity
     *
     * @param entity is the entity to be swapped position with
     * @throws WireframeException when one of them is not in the list or is locked
     */
    public void swapWith(WireframeEntity entity) throws WireframeException {
        assert WireFrame.currentEntities.contains(this);
        assert WireFrame.currentEntities.contains(entity);

        try {
            validateSwappable(entity);
        } catch (WireframeException e) {
            logError(e);
        }

        int index1 = WireFrame.currentEntities.indexOf(this);
        int index2 = WireFrame.currentEntities.indexOf(entity);
        Collections.swap(WireFrame.currentEntities, index1, index2);
    }

    //////////////////////////////////
    // HELPER
    //////////////////////////////////

    void logError(WireframeException e) {
        ErrorHandler.logError(e);
        System.err.printf("Error: %s\n", e.toString());
        thrown = true;
    }

    //////////////////////////////////
    // ACCESSORS
    //////////////////////////////////

    int getXCoordinate() {
        return xCoordinate;
    }

    int getYCoordinate() {
        return yCoordinate;
    }

    void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    void setHeight(int height) {
        this.height = height;
    }

    void setWidth(int width) {
        this.width = width;
    }

    boolean getIsLocked() {
        return isLocked;
    }

    void setLocked(boolean lock) {
        isLocked = lock;
    }

    //////////////////////////////////
    // VALIDATIONS
    //////////////////////////////////

    /**
     * validate if this enetity is locked
     *
     * @throws WireframeException when it is locked or not in the list
     */
    void validateModifiable() throws WireframeException {
        assert WireFrame.currentEntities.contains(this);

        if (getIsLocked())
            throw new WireframeException(WireframeException.ErrorCode.ENTITY_LOCKED);
    }

    /**
     * validate if this and that entities can be swapped
     *
     * @param entity is the entity to be swapped with this
     * @throws WireframeException when one of them is locked or not in the list
     */
    void validateSwappable(WireframeEntity entity) throws WireframeException {
        // both entities and annotation are not locked
        validateModifiable();
        entity.validateModifiable();
    }

    //////////////////////////////////
    // ABSTRACT METHOD
    //////////////////////////////////

    public abstract void delete();
}
