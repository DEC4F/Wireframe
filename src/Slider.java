import javax.swing.*;

public class Slider extends InteractableElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    Slider(int xCoordinate, int yCoordinate, int height, int width) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.component = new JSlider();

        WireFrame.currentEntities.add(this);
    }

}
