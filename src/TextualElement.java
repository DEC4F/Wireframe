import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public abstract class TextualElement extends Element {

    //////////////////////////////////
    // FIELD
    //////////////////////////////////

    Text textMsg = new Text();

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

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

    String getMsg () {
        return textMsg.getText();
    }

    TextAlignment getAlignment() {
        return textMsg.getTextAlignment();
    }

}
