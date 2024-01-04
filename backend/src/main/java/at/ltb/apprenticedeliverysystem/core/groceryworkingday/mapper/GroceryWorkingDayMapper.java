package at.ltb.apprenticedeliverysystem.core.groceryworkingday.mapper;

import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayEntity;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.CreateGroceryWorkingDayDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.UpdateGroceryWorkingDayDTO;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.mapper.UserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {UserMapper.class})
public abstract class GroceryWorkingDayMapper {

    @Mapping(target = "id", source = "uuid")
    public abstract GroceryWorkingDayDetailDTO mapGroceryWorkingDayEntityToDetail(GroceryWorkingDayEntity groceryWorkingDay);

    public abstract List<GroceryWorkingDayDetailDTO> mapGroceryWorkingDayEntityToDetail(List<GroceryWorkingDayEntity> groceryWorkingDays);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "goingUsers", ignore = true)
    @Mapping(target = "payingUser", ignore = true)
    public abstract GroceryWorkingDayEntity mapCreateGroceryWorkingDayToEntity(CreateGroceryWorkingDayDTO groceryWorkingDay);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "goingUsers", source = ".", qualifiedByName = "goingUsers")
    @Mapping(target = "payingUser", source = ".", qualifiedByName = "payingUser")
    public abstract GroceryWorkingDayEntity mapUpdateGroceryWorkingDayToEntity(
            UpdateGroceryWorkingDayDTO groceryWorkingDay, @MappingTarget GroceryWorkingDayEntity target);

    @Named("goingUsers")
    protected List<UserEntity> goingUsers(UpdateGroceryWorkingDayDTO groceryWorkingDay) {
        return new ArrayList<>();
    }

    @Named("payingUser")
    protected UserEntity payingUser(UpdateGroceryWorkingDayDTO groceryWorkingDay) {
        return null;
    }
}
