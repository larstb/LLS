package at.ltb.apprenticedeliverysystem.core.product.mapper;

import at.ltb.apprenticedeliverysystem.core.category.mapper.CategoryMapper;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductEntity;
import at.ltb.apprenticedeliverysystem.core.product.dto.CreateProductDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.UpdateProductDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {CategoryMapper.class})
public abstract class ProductMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "categoryName", source = "category.name")
    public abstract ProductOverviewDTO mapProductEntityToOverview(ProductEntity product);

    public abstract List<ProductOverviewDTO> mapProductEntityToOverview(List<ProductEntity> product);

    @Mapping(target = "id", source = "uuid")
    public abstract ProductDetailDTO mapProductEntityToDetail(ProductEntity product);

    public abstract List<ProductDetailDTO> mapProductEntityToDetail(List<ProductEntity> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "category", ignore = true)
    public abstract ProductEntity mapCreateProductToEntity(CreateProductDTO product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "category", ignore = true)
    public abstract ProductEntity mapUpdateProductToEntity(UpdateProductDTO productDTO, @MappingTarget ProductEntity target);
}
