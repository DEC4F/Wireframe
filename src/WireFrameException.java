class WireframeException extends Exception {

    enum ErrorCode {
        ENTITY_LOCKED,
        ENTITY_ALREADY_IN_GROUP,
        CANNOT_ANNOTATE_AN_ANNOTATION,
        GROUP_MEMBER_SHOULD_BE_LOCKED
    }

    private ErrorCode errorCode;

    WireframeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "WireFrameException{" +
                "errorCode=" + errorCode +
                '}';
    }
}
