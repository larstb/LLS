package at.ltb.apprenticedeliverysystem.core.category.mapper;

import at.ltb.apprenticedeliverysystem.core.category._persistence.CategoryEntity;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryDetailDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CreateCategoryDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.UpdateCategoryDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CategoryMapper {

    @Mapping(target = "id", source = "uuid")
    public abstract CategoryOverviewDTO mapCategoryEntityToOverview(CategoryEntity category);

    public abstract List<CategoryOverviewDTO> mapCategoryEntityToOverview(List<CategoryEntity> category);

    @Mapping(target = "id", source = "uuid")
    public abstract CategoryDetailDTO mapCategoryEntityToDetail(CategoryEntity category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    public abstract CategoryEntity mapCreateCategoryToEntity(CreateCategoryDTO category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    public abstract CategoryEntity mapUpdateCategoryToEntity(UpdateCategoryDTO category, @MappingTarget CategoryEntity target);
}
