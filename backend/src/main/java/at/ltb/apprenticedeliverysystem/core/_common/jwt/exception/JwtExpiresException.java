package at.ltb.apprenticedeliverysystem.core._common.jwt.exception;

public class JwtExpiresException extends RuntimeException {

    public JwtExpiresException(String message) {
        super(message);
    }
}
