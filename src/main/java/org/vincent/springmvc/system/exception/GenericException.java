package org.vincent.springmvc.system.exception;

public class GenericException extends RuntimeException {

    private final String errorMessage;

    public GenericException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public GenericException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public GenericException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public GenericException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public GenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
