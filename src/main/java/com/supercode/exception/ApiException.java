package com.supercode.exception;

public class ApiException extends Exception{

    private static final long serialVersionUID = 1L;

    private String message;
    private String details;
    private Exception exception;

    public ApiException(String message, String details, Exception ex){
        this.message = message;
        this.details = details;
        this.exception = ex;
    }

    public ApiException(String message, String details){
        this.message = message;
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "message='" + message + '\'' +
                ", details='" + details + '\'' +
                ", exception=" + exception +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiException that = (ApiException) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        return exception != null ? exception.equals(that.exception) : that.exception == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (exception != null ? exception.hashCode() : 0);
        return result;
    }
}
