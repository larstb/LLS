package at.ltb.apprenticedeliverysystem.configuration.exceptionHandling;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message) {
        super(message);
    }
}
