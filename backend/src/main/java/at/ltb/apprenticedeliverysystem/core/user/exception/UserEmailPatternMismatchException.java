package at.ltb.apprenticedeliverysystem.core.user.exception;

public class UserEmailPatternMismatchException extends RuntimeException {

    public UserEmailPatternMismatchException(String message) {
        super(message);
    }
}
