package com.lms.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String userId) {
        super("User not found: " + userId);
    }
}
