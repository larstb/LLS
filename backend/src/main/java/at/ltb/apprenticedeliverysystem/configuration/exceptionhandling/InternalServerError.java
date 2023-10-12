package at.ltb.apprenticedeliverysystem.configuration.exceptionhandling;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message) {
        super(message);
    }
}
