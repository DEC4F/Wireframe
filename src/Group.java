import java.util.ArrayList;
import java.util.List;

public class Group extends WireframeEntity {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    // stores grouped entity in this group
    private List<WireframeEntity> entityList;

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    Group(int xCoordinate, int yCoordinate, int height, int width) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.height = height;
        this.width = width;
        this.entityList = new ArrayList<>();

        WireFrame.currentEntities.add(this);
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    public void group (List<? extends WireframeEntity> entities) {
        assert entities != null;
        assert !entities.isEmpty();

        try {
            int groupX = Integer.MAX_VALUE;
            int groupY = Integer.MIN_VALUE;
            int bottomSideY = Integer.MAX_VALUE;
            int rightSideX = Integer.MIN_VALUE;

            for (int i = 0; i < entities.size(); i++) {
                validateGrouping(entities.get(i));
                entityList.add(entities.get(i));

                // once grouped, individual entity is locked (can't move/deleted unless as a group)
                entities.get(i).setLocked(true);

                // find smallest X and biggest Y as group coordinates
                groupX = Math.min(groupX, entities.get(i).getXCoordinate());
                groupY = Math.max(groupY, entities.get(i).getYCoordinate());

                // find biggest X and smallest Y as corner coordinates to calculate height&width
                bottomSideY = Math.min(bottomSideY, entities.get(i).getYCoordinate() - entities.get(i).getWidth());
                rightSideX = Math.max(rightSideX, entities.get(i).getXCoordinate() + entities.get(i).getWidth());
            }
            // move to new coordinates
            moveTo(groupX, groupY);

            // modify height & width
            int groupHeight = groupY - bottomSideY;
            int groupWidth = rightSideX - groupX;
            setHeight(groupHeight);
            setWidth(groupWidth);
        } catch (WireframeException e) {
            logError(e);
        }
    }

    //////////////////////////////////
    // ACCESSORS
    //////////////////////////////////

    List<WireframeEntity> getEntityList() {
        return entityList;
    }

    //////////////////////////////////
    // VALIDATION
    //////////////////////////////////

    /**
     * validate if a entity is ready to be grouped
     * @param entity is the entity to be grouped
     * @throws WireframeException when entity is already in the group or either the group or the entity is locked
     */
    private void validateGrouping(WireframeEntity entity) throws WireframeException {
        if (entityList.contains(entity))
            throw new WireframeException(WireframeException.ErrorCode.ENTITY_ALREADY_IN_GROUP);
        validateModifiable();
        entity.validateModifiable();
    }

    /**
     * delete every member of this group and itself from the Wireframe
     */
    @Override
    public void delete() {
        try {
            validateModifiable();
            // delete everything in the group
            for (int i = 0; i < entityList.size(); i++) {
                //throw exception if not locked
                if (!entityList.get(i).getIsLocked())
                    throw new WireframeException(WireframeException.ErrorCode.GROUP_MEMBER_SHOULD_BE_LOCKED);

                // unlock and delete
                entityList.get(i).setLocked(false);
                entityList.get(i).validateModifiable();
                entityList.get(i).delete();
            }
            entityList.clear();
            WireFrame.currentEntities.remove(this);
        } catch (WireframeException e) {
            logError(e);
        }
    }

    static class GroupTestHook {
        static void validateGrouping(WireframeEntity entity) throws WireframeException {
            Group group = new Group(0,0,0,0);
            group.validateGrouping(entity);
        }
    }

}