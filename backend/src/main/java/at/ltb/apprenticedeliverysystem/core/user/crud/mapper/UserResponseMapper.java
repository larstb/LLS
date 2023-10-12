package at.ltb.apprenticedeliverysystem.core.user.crud.mapper;

import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.crud.models.UserDTO;
import at.ltb.apprenticedeliverysystem.core.user.crud.models.UserOverviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public abstract class UserResponseMapper {

    public abstract List<UserOverviewDTO> map(List<UserEntity> source);
    public abstract UserDTO mapDto(UserEntity source);
}
