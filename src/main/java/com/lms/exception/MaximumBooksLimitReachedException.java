package com.lms.exception;

public class MaximumBooksLimitReachedException extends UserException {

    public MaximumBooksLimitReachedException(String userId) {
        super("User has already reached the maximum book limit: " + userId);
    }
}
