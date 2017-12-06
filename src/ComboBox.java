import javax.swing.*;

/** Combo box class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
public class ComboBox extends InteractiveElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate a combo box
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     */
    public ComboBox (int xCoordinate, int yCoordinate, int height, int width) {
        super(xCoordinate, yCoordinate, height, width);
        setComponent(new JComboBox());
    }

}
