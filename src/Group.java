import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Group class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
public class Group extends WireframeEntity {

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    /** stores grouped entity in this group */
    private List<WireframeEntity> entityList;

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate a group
     *
     * @param xCoordinate is x coordinate of it
     * @param yCoordinate is y coordinate of it
     * @param height      is height of it
     * @param width       is width of it
     */
    public Group(int xCoordinate, int yCoordinate, int height, int width) {
        super(xCoordinate, yCoordinate, height, width);
        this.entityList = new ArrayList<>();
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    /** groups two or more input wireframe entities
     *
     * @param entities is the list of entities to be grouped
     */
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
                bottomSideY = Math.min(bottomSideY, entities.get(i).getYCoordinate() - entities.get(i).getHeight());
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

    /** delete every member of this group and the group itself from the Wireframe */
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
            Wireframe.currentEntities.remove(this);
        } catch (WireframeException e) {
            logError(e);
        }
    }

    //////////////////////////////////
    // ACCESSORS
    //////////////////////////////////

    /** get the list of entities in this group */
    List<WireframeEntity> getEntityList() {
        return entityList;
    }

    //////////////////////////////////
    // VALIDATION
    //////////////////////////////////

    /** validate if a entity is ready to be grouped
     *
     * @param entity is the entity to be grouped
     * @throws WireframeException when entity is already in the group or either the group or the entity is locked or it's annotation
     */
    private void validateGrouping(WireframeEntity entity) throws WireframeException {
        // will fail if this entity is annotation or not included into wireframe when created
        assert Wireframe.currentEntities.contains(entity);

        if (entityList.contains(entity))
            throw new WireframeException(WireframeException.ErrorCode.ENTITY_ALREADY_IN_GROUP);
        validateModifiable();
        entity.validateModifiable();
    }

    //////////////////////////////////
    // TEST HOOKS
    //////////////////////////////////

    /** test hook class for testing private method */
    static class GroupTestHook {
        /** private method to be tested */
        static void validateGrouping(WireframeEntity entity) throws WireframeException {
            Group group = new Group(10,10,10,10);
            group.validateGrouping(entity);
        }

        /** customized test hook method for testing entity already in group */
        static void validateGroupingLastTest(WireframeEntity entity) throws WireframeException {
            Group group = new Group(10,10,10,10);
            List<? extends WireframeEntity> entities = Arrays.asList(entity);
            group.group(entities);
            group.validateGrouping(entity);
        }
    }

}