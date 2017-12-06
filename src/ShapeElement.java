import javafx.scene.shape.Shape;

/** Shape element class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
abstract class ShapeElement extends Element {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    /** the type of shape of this shape element */
    Shape shape;

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate a shape element
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     */
    ShapeElement (int xCoordinate, int yCoordinate, int height, int width) {
        super(xCoordinate, yCoordinate, height, width);
    }

    //////////////////////////////////
    // ACCESSORS
    //////////////////////////////////

    /** get the type of shape of this shape element */
    Shape getShape() {
        return shape;
    }

    /** set the type of shape of this shape element */
    void setShape(Shape shape) {
        this.shape = shape;
    }
}