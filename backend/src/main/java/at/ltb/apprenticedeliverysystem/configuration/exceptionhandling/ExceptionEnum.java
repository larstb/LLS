package at.ltb.apprenticedeliverysystem.configuration.exceptionhandling;

import at.ltb.apprenticedeliverysystem.core._common.auth.exception.JwtClaimException;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ExceptionEnum {
    JWT_CLAIM_EXCEPTION(JwtClaimException.class,
            "JWT_CLAIM_EXCEPTION",
            HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_WRONG_ROLE(Object.class,
            "UNAUTHORIZED_WRONG_ROLE",
            HttpStatus.UNAUTHORIZED),
    ENTITY_NOT_FOUND(EntityNotFoundException.class,
            "ENTITY_NOT_FOUND",
            HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(InternalServerError.class,
            "INTERNAL_SERVER_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR);

    private final Class<?> clazz;

    private final String code;

    private final HttpStatus httpStatus;

    ExceptionEnum(final Class<?> clazz, final String code, final HttpStatus httpStatus) {
        this.clazz = clazz;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public static ExceptionEnum getException(final Class<?> clazz) {
        return Arrays.stream(ExceptionEnum.values())
                .filter(exception -> exception.getClazz().equals(clazz))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}
