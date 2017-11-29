import javafx.scene.shape.Rectangle;

public class Box extends ShapeElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    Box(int xCoordinate, int yCoordinate, int height, int width) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.shape = new Rectangle();

        WireFrame.currentEntities.add(this);
    }

}