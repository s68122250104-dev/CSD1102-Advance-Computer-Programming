class MyTestException extends RuntimeException {

    private int errorCode;

    public MyTestException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}