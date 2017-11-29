import javax.swing.*;

public class ScrollBar extends InteractableElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    ScrollBar(int xCoordinate, int yCoordinate, int height, int width) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.component = new JScrollBar();

        WireFrame.currentEntities.add(this);
    }

}
