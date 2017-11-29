
abstract class Element extends WireframeEntity {

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /**
     * resize the element
     * @param height is the height
     * @param width is the width
     */
    public void resize (int height, int width) {
        assert height >= 0;
        assert width >= 0;

        try {
            validateModifiable();
        } catch (WireframeException e) {
            logError(e);
        }
        setHeight(height);
        setWidth(width);
    }

    /**
     * remove itself from the Wireframe
     */
    @Override
    public void delete() {
        try {
            validateModifiable();
            WireFrame.currentEntities.remove(this);
        }catch (WireframeException e) {
            logError(e);
        }
    }

}