/** Element class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
abstract class Element extends WireframeEntity {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate an element
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     */
    Element (int xCoordinate, int yCoordinate, int height, int width) {
        super(xCoordinate, yCoordinate, height, width);
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /** resize the element
     * @param height is the height
     * @param width is the width
     */
    public void resize (int height, int width) {
        assert height >= 0;
        assert width >= 0;

        try {
            validateModifiable();
        } catch (WireframeException e) {
            logError(e);
        }
        setHeight(height);
        setWidth(width);
    }

    /** remove this element from the Wireframe */
    @Override
    public void delete() {
        try {
            validateModifiable();
            if (getClass() == Annotation.class)
                Wireframe.annotations.remove(this);
            else
                Wireframe.currentEntities.remove(this);
        }catch (WireframeException e) {
            logError(e);
        }
    }

}