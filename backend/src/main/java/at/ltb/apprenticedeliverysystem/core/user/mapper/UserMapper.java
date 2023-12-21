package at.ltb.apprenticedeliverysystem.core.user.mapper;

import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;
import at.ltb.apprenticedeliverysystem.core.keycloak.KeyCloakService;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.dto.*;
import at.ltb.apprenticedeliverysystem.core.user.dto.PortalUserDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import javax.inject.Inject;
import java.util.List;

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

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    public abstract UserDetailDTO mapUserEntityToDetailForUser(UserEntity user);

    @Mapping(target = "roles", ignore = true)
    public abstract PortalUserDTO mapUserEntityToPortalUser(UserEntity user);

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
    public abstract UserEntity mapUpdateUserToEntity(UpdatePortalUserDTO user, @MappingTarget UserEntity target);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "keycloakReference", ignore = true)
    public abstract UserEntity mapUpdateUserToEntityForUser(UpdateUserDTO user, @MappingTarget UserEntity target);

    @AfterMapping
    public void afterMappingMapUserEntityToDetailPortal(UserEntity source, @MappingTarget UserDetailDTO target) {
        target.setEnabled(keyCloakService.loadEnabledByKeyCloakId(source.getKeycloakReference()));
        target.setRoles(RoleEnum.findListByKeyCloakName(keyCloakService.loadGroupNamesByKeyCloakId(source.getKeycloakReference())));
    }
}
