package at.ltb.apprenticedeliverysystem.core.user;

import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;
import at.ltb.apprenticedeliverysystem.core.keycloak.KeyCloakService;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserMapper {

    @Inject
    private KeyCloakService keyCloakService;

    @Mapping(target = "id", source = "uuid")
    public abstract UserOverviewDTO mapUserEntityToOverview(UserEntity user);

    public abstract List<UserOverviewDTO> mapUserEntityToOverview(List<UserEntity> user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", source = "uuid")
    public abstract UserDetailDTO mapUserEntityToDetail(UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "keycloakReference", ignore = true)
    public abstract UserEntity mapCreateUserToEntity(CreateUserDTO user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "keycloakReference", ignore = true)
    public abstract UserEntity mapUpdateUserToEntity(UpdateUserDTO user, @MappingTarget UserEntity target);

    @AfterMapping
    public void afterMappingMapUserEntityToDetail(UserEntity source, @MappingTarget UserDetailDTO target) {
        target.setEnabled(keyCloakService.loadEnabledByKeyCloakId(source.getKeycloakReference()));
        target.setRoles(RoleEnum.findListByKeyCloakName(keyCloakService.loadGroupNamesByKeyCloakId(source.getKeycloakReference())));
    }
}
