package at.ltb.apprenticedeliverysystem.core._common.role;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("ROLE_ADMIN"),
    MODERATOR("ROLE_MODERATOR"),
    USER("ROLE_USER");

    private final String authority;

    RoleEnum(String authority) {
        this.authority = authority;
    }

}
