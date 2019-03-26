package com.supercode.exception;

public class UsernameAlreadyInUseException extends Exception {

    public UsernameAlreadyInUseException() {
        super("Username already in use.");
    }

}
