package at.ltb.apprenticedeliverysystem.core.user.exception;

public class UserEmailNotUniqueException extends RuntimeException {
    public UserEmailNotUniqueException(String message) {
        super(message);
    }
}
