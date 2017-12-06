import java.util.Collections;

/** Wireframe entity class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
abstract class WireframeEntity {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    /** the x coordinate of the entity */
    private int xCoordinate;
    /** the y coordinate of the entity */
    private int yCoordinate;
    /** the height of the entity */
    private int height;
    /** the width of the entity */
    private int width;
    /** indicates if this entity is locked */
    private boolean isLocked = false;
    /** debug aid for testing if try-catch is functioning */
    private static boolean thrown = false;

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    /** instantiate a wireframe entity, will be added to wireframe automatically when created
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     */
    WireframeEntity (int xCoordinate, int yCoordinate, int height, int width) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;

        Wireframe.currentEntities.add(this);
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /** changes the coordinates of this entity
     *
     * @param newX is the new x coordinates of the entity
     * @param newY is the new y coordinates of the entity
     */
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

    /** changes the index of this entity in the currentEntities to 0
     *
     * @throws WireframeException when this entity is locked or not in the list
     */
    public void bringToFront() throws WireframeException {
        assert Wireframe.currentEntities.contains(this);

        try {
            validateModifiable();
        } catch (WireframeException e) {
            logError(e);
        }
        // remove this entity from old pos and then add to head of the list
        Wireframe.currentEntities.remove(Wireframe.currentEntities.indexOf(this));
        Wireframe.currentEntities.add(0, this);
    }

    /** swaps the position of this and parameter entity
     *
     * @param entity is the entity to be swapped position with
     * @throws WireframeException when one of them is not in the list or is locked
     */
    public void swapWith(WireframeEntity entity) throws WireframeException {
        assert Wireframe.currentEntities.contains(this);
        assert Wireframe.currentEntities.contains(entity);

        try {
            validateSwappable(entity);
        } catch (WireframeException e) {
            logError(e);
        }

        int index1 = Wireframe.currentEntities.indexOf(this);
        int index2 = Wireframe.currentEntities.indexOf(entity);
        Collections.swap(Wireframe.currentEntities, index1, index2);
    }

    //////////////////////////////////
    // HELPER
    //////////////////////////////////

    /** logs error to log.txt, prints error to console and set thrown to true */
    void logError(WireframeException e) {
        ErrorHandler.logError(e);
        System.err.printf("Error: %s\n", e.toString());
        thrown = true;
    }

    //////////////////////////////////
    // ACCESSORS
    //////////////////////////////////

    /** gets the x coordinate of the entity */
    int getXCoordinate() {
        return xCoordinate;
    }

    /** gets the y coordinate of the entity */
    int getYCoordinate() {
        return yCoordinate;
    }

    /** sets the x coordinate of the entity */
    void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /** sets the y coordinate of the entity */
    void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /** gets the height of the entity */
    int getHeight() {
        return height;
    }

    /** gets the width of the entity */
    int getWidth() {
        return width;
    }

    /** sets the height of the entity */
    void setHeight(int height) {
        this.height = height;
    }

    /** sets the width of the entity */
    void setWidth(int width) {
        this.width = width;
    }

    /** gets the lock state of the entity */
    boolean getIsLocked() {
        return isLocked;
    }

    /** sets the lock state of the entity */
    void setLocked(boolean lock) {
        isLocked = lock;
    }

    /** gets the thrown state of the entity */
    static boolean isThrown() {
        return thrown;
    }

    /** sets the thrown state of the entity */
    static void setThrown(boolean thrown) {
        WireframeEntity.thrown = thrown;
    }

    //////////////////////////////////
    // VALIDATIONS
    //////////////////////////////////

    /** validate if this entity is locked
     *
     * @throws WireframeException when it is locked or not in the list
     */
    void validateModifiable() throws WireframeException {
        assert Wireframe.currentEntities.contains(this);

        if (getIsLocked())
            throw new WireframeException(WireframeException.ErrorCode.ENTITY_LOCKED);
    }

    /** validate if this and that entities can be swapped
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

    /** delete the entity from wireframe */
    public abstract void delete();
}
