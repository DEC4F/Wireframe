import javafx.scene.shape.Rectangle;

public class Headline extends ShapeElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    Headline(int xCoordinate, int yCoordinate, int height, int width) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.shape = new Rectangle();

        WireFrame.currentEntities.add(this);
    }
}
