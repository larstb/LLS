package at.ltb.apprenticedeliverysystem.core.order.exception;

public class CurrentUserIsNotPayingUserException extends RuntimeException {
    public CurrentUserIsNotPayingUserException(String message) {
        super(message);
    }
}
