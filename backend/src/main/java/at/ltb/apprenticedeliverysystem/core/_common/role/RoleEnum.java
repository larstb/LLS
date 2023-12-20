package at.ltb.apprenticedeliverysystem.core._common.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN("Admin", "ROLE_ADMIN"),
    MOD("Moderator", "ROLE_MODERATOR"),
    USER("User", "ROLE_USER");

    private final String keyCloakName;

    private final String applicationRole;

    public static RoleEnum findByKeyCloakName(String name) {
        return Arrays.stream(RoleEnum.values())
                .filter(val -> val.getKeyCloakName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<RoleEnum> findListByKeyCloakName(List<String> roles) {
        return roles
                .stream()
                .map(RoleEnum::findByKeyCloakName)
                .collect(Collectors.toList());
    }
}
