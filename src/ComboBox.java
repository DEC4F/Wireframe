import javax.swing.*;

public class ComboBox extends InteractableElement {

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    ComboBox (int xCoordinate, int yCoordinate, int height, int width) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.component = new JComboBox();

        WireFrame.currentEntities.add(this);
    }

}
