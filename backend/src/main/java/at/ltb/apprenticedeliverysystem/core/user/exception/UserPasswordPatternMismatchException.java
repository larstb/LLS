package at.ltb.apprenticedeliverysystem.core.user.exception;

public class UserPasswordPatternMismatchException extends RuntimeException {

    public UserPasswordPatternMismatchException(String message) {
        super(message);
    }
}
