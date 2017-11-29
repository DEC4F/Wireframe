
public class CannedText extends TextualElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    CannedText(int xCoordinate, int yCoordinate, int height, int width, String message) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.textMsg.setText(message);

        WireFrame.currentEntities.add(this);
    }

}
