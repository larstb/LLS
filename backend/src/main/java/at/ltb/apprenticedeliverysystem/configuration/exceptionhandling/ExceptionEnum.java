package at.ltb.apprenticedeliverysystem.configuration.exceptionhandling;

import at.ltb.apprenticedeliverysystem.core._common.auth.exception.JwtClaimException;
import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core.category.exception.CategoryNameNotUniqueException;
import at.ltb.apprenticedeliverysystem.core.category.exception.CategoryUpdateException;
import at.ltb.apprenticedeliverysystem.core.keycloak.exception.KeyCloakConfigException;
import at.ltb.apprenticedeliverysystem.core.product.exception.ProductCreateCategoryIdException;
import at.ltb.apprenticedeliverysystem.core.product.exception.ProductNameNotUniqueException;
import at.ltb.apprenticedeliverysystem.core.product.exception.ProductUpdateException;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserEmailNotUniqueException;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserUpdateException;
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
    USER_EMAIL_NOT_UNIQUE(UserEmailNotUniqueException.class,
            "USER_EMAIL_NOT_UNIQUE",
            HttpStatus.BAD_REQUEST),
    USER_UPDATE_EXCEPTION(UserUpdateException.class,
            "USER_UPDATE_EXCEPTION",
            HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_NOT_UNIQUE(CategoryNameNotUniqueException.class,
            "CATEGORY_NAME_NOT_UNIQUE",
            HttpStatus.BAD_REQUEST),
    CATEGORY_UPDATE_EXCEPTION(CategoryUpdateException.class,
            "CATEGORY_UPDATE_EXCEPTION",
            HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_NOT_UNIQUE(ProductNameNotUniqueException.class,
            "PRODUCT_NAME_NOT_UNIQUE",
            HttpStatus.BAD_REQUEST),
    PRODUCT_CREATE_CATEGORY_ID_EXCEPTION(ProductCreateCategoryIdException.class,
            "PRODUCT_CREATE_CATEGORY_ID_EXCEPTION",
            HttpStatus.BAD_REQUEST),
    PRODUCT_UPDATE_EXCEPTION(ProductUpdateException.class,
            "PRODUCT_UPDATE_EXCEPTION",
            HttpStatus.BAD_REQUEST),
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
