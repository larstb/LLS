package at.ltb.apprenticedeliverysystem.core.orderitem;

import at.ltb.apprenticedeliverysystem.core.orderitem._persistence.OrderItemEntity;
import at.ltb.apprenticedeliverysystem.core.orderitem.dto.OrderItemDetailDTO;
import at.ltb.apprenticedeliverysystem.core.orderitem.dto.OrderItemOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.product.mapper.ProductMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {ProductMapper.class})
public abstract class OrderItemMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "productName", source = "product.name")
    public abstract OrderItemOverviewDTO mapOrderItemEntityToOverview(OrderItemEntity orderItem);

    public abstract List<OrderItemOverviewDTO> mapOrderItemEntityToOverview(List<OrderItemEntity> orderItems);

    @Mapping(target = "id", source = "uuid")
    public abstract OrderItemDetailDTO mapOrderItemEntityToDetail(OrderItemEntity orderItem);

    public abstract List<OrderItemDetailDTO> mapOrderItemEntityToDetail(List<OrderItemEntity> orderItems);
}
