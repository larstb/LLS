package at.ltb.apprenticedeliverysystem.core.user.exception;

public class UserEmailUniqueException extends RuntimeException {

    public UserEmailUniqueException(String message) {
        super(message);
    }
}
