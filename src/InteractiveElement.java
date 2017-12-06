import javax.swing.*;

/** Interactive element class class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
abstract class InteractiveElement extends Element{

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    /** the type of JComponent of this element */
    JComponent component;

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate an interactive element
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     */
    InteractiveElement(int xCoordinate, int yCoordinate, int height, int width) {
        super(xCoordinate, yCoordinate, height, width);
    }

    //////////////////////////////////
    // ACCESSORS
    //////////////////////////////////

    /** get the type of component */
    JComponent getComponent() {
        return component;
    }

    /** set the type of component */
    void setComponent(JComponent component) {
        this.component = component;
    }

}
