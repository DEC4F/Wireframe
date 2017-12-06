import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/** Textual element class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
abstract class TextualElement extends Element {

    //////////////////////////////////
    // FIELD
    //////////////////////////////////

    /** the text of this textual element */
    Text textMsg = new Text();

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate a textual element
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     * @param message     is the message it contains
     */
    TextualElement (int xCoordinate, int yCoordinate, int height, int width, String message) {
        super(xCoordinate, yCoordinate, height, width);
        this.textMsg.setText(message);
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /** aligns the stored text
     *
     * @param alignment is the type of alignment of the text
     */
    public void align(TextAlignment alignment) {
        try {
            validateModifiable();
            textMsg.setTextAlignment(alignment);
        }catch (WireframeException e) {
            logError(e);
        }
    }

    //////////////////////////////////
    // ACCESSOR
    //////////////////////////////////

    /** get the text of the textual element */
    Text getTextMsg() {
        return textMsg;
    }

    /** get the stored message of the textual element */
    String getMsg () {
        return textMsg.getText();
    }

    /** get the type of alignment of the textual element */
    TextAlignment getAlignment() {
        return textMsg.getTextAlignment();
    }

}
