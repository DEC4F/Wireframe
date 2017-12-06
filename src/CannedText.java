/** Canned text class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
public class CannedText extends TextualElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate a canned text
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     * @param message     is the message of this canned text
     */
    public CannedText(int xCoordinate, int yCoordinate, int height, int width, String message) {
        super(xCoordinate, yCoordinate, height, width, message);
    }

}
