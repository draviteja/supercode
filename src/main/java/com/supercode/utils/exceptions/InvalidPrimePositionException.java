package com.supercode.utils.exceptions;

public class InvalidPrimePositionException extends RuntimeException {

    private static final long serialVersionUID = -2654023435704800478L;

    private static final String EXCEPTION_INFO = "Prime numbers cannot be "
            + "negative, thus the position must be greater or equal to one";


    public InvalidPrimePositionException() {
        super(EXCEPTION_INFO);
    }


    public InvalidPrimePositionException(String message) {
        super(message);
    }


    public InvalidPrimePositionException(Throwable cause) {
        super(cause);
    }


    public InvalidPrimePositionException(String message, Throwable cause) {
        super(EXCEPTION_INFO, cause);
    }
}