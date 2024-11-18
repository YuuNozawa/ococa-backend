package dev.ococa.api.domain.exception;

public class UnauthorizedMoodAccessException extends RuntimeException {
    public UnauthorizedMoodAccessException() {
        super("User is not authorized for this mood.");
    }
}
