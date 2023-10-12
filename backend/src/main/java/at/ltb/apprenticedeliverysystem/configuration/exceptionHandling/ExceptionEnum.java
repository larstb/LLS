package at.ltb.apprenticedeliverysystem.configuration.exceptionHandling;

import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtClaimException;
import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtExpiresException;
import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtTokenException;
import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtUserException;
import at.ltb.apprenticedeliverysystem.core.user.exception.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ExceptionEnum {

    USER_EMAIL_NOT_UNIQUE(UserEmailUniqueException.class,
            "USER_EMAIL_NOT_UNIQUE",
            HttpStatus.BAD_REQUEST),
    USER_EMAIL_PATTERN_MISMATCH(UserEmailPatternMismatchException.class,
            "USER_EMAIL_PATTERN_MISMATCH",
            HttpStatus.BAD_REQUEST),
    USER_LOGIN(UserLoginException.class,
            "USER_LOGIN_EXCEPTION",
            HttpStatus.FORBIDDEN),
    USER_PHONE_NUMBER_PATTERN_MISMATCH(UserPhoneNumberPatternMismatchException.class,
            "USER_PHONE_NUMBER_PATTERN_MISMATCH",
            HttpStatus.BAD_REQUEST),
    USER_PASSWORD_PATTERN_MISMATCH(UserPasswordPatternMismatchException.class,
            "USER_PASSWORD_PATTERN_MISMATCH",
            HttpStatus.BAD_REQUEST),
    JWT_USER(JwtUserException.class,
            "JWT_USER",
            HttpStatus.UNAUTHORIZED),
    JWT_TOKEN(JwtTokenException.class,
            "JWT_TOKEN",
            HttpStatus.UNAUTHORIZED),
    JWT_EXPIRED(JwtExpiresException.class,
            "JWT_EXPIRED",
            HttpStatus.UNAUTHORIZED),
    JWT_CLAIM(JwtClaimException.class,
            "JWT_CLAIM",
            HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_WRONG_ROLE(JwtClaimException.class,
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
