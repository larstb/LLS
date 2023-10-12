package at.ltb.apprenticedeliverysystem.core.user.crud.mapper;

import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.crud.configuration.UserPasswordProperties;
import at.ltb.apprenticedeliverysystem.core.user.crud.models.UserCreateDTO;
import at.ltb.apprenticedeliverysystem.core.user.crud.models.UserDTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public abstract class UserRequestMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPasswordProperties userPasswordProperties;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "password", source = "firstname", qualifiedByName = "password")
    public abstract UserEntity mapCreate(UserCreateDTO source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract void mapUpdate(@MappingTarget UserEntity userToSave, UserDTO source);

    @Named("password")
    protected String password(String firstname) {
        return passwordEncoder.encode(userPasswordProperties.getInitialPassword() + firstname);
    }
}
