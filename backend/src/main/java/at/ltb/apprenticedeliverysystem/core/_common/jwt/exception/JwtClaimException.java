package at.ltb.apprenticedeliverysystem.core._common.jwt.exception;

public class JwtClaimException extends RuntimeException {

    public JwtClaimException(String message) {
        super(message);
    }
}
