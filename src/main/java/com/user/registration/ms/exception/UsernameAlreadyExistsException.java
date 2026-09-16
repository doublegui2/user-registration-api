package com.user.registration.ms.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Username " + username + " is not available. Please choose another username.");
    }
}
