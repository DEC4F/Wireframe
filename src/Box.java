import javafx.scene.shape.Rectangle;

/** Box class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
public class Box extends ShapeElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate a box
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     */
    public Box(int xCoordinate, int yCoordinate, int height, int width) {
        super(xCoordinate, yCoordinate, height, width);
        setShape(new Rectangle());
    }

}