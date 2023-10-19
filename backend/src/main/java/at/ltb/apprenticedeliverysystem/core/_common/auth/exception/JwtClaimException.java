package at.ltb.apprenticedeliverysystem.core._common.auth.exception;

public class JwtClaimException extends RuntimeException {
    public JwtClaimException(String message) {
        super(message);
    }
}
