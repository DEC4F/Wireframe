/** Exception class of Wireframe
 *
 * @author Stanley Tian
 * @version 1.0 Dec/06/2017
 */
class WireframeException extends Exception {

    //////////////////////////////////
    // ENUM
    //////////////////////////////////

    enum ErrorCode {
        ENTITY_LOCKED,
        ENTITY_ALREADY_IN_GROUP,
        CANNOT_ANNOTATE_AN_ANNOTATION,
        GROUP_MEMBER_SHOULD_BE_LOCKED
    }

    //////////////////////////////////
    // FIELDS
    //////////////////////////////////

    private ErrorCode errorCode;

    //////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////

    /** instantiate an wireframe exception
     *
     * @param errorCode is the error code of this exception
     */
    WireframeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    //////////////////////////////////
    // INTERFACE
    //////////////////////////////////

    @Override
    public String toString() {
        return "WireFrameException{" +
                "errorCode=" + errorCode +
                '}';
    }
}
