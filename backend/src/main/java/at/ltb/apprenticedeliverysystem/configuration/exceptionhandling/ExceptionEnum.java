package at.ltb.apprenticedeliverysystem.configuration.exceptionhandling;

import at.ltb.apprenticedeliverysystem.core._common.auth.exception.JwtClaimException;
import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core.keycloak.exception.KeyCloakConfigException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    ENTITY_NOT_FOUND(CustomEntityNotFoundException.class,
            "ENTITY_NOT_FOUND",
            HttpStatus.NOT_FOUND),
    KEYCLOAK_CONFIG_ERROR_ADMIN_CLIENT(KeyCloakConfigException.class,
            "KEYCLOAK_CONFIG_ERROR_ADMIN_CLIENT",
            HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(AccessDeniedException.class,
            "Unauthorized",
            HttpStatus.UNAUTHORIZED),
    JWT_CLAIM_EXCEPTION(JwtClaimException.class,
            "JWT_CLAIM_EXCEPTION",
            HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR(InternalServerError.class,
            "INTERNAL_SERVER_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR);

    private final Class<?> clazz;

    private final String code;

    private final HttpStatus httpStatus;

    public static ExceptionEnum getException(final Class<?> clazz) {
        return Arrays.stream(ExceptionEnum.values())
                .filter(exception -> exception.getClazz().equals(clazz))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}
