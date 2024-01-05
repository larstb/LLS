package at.ltb.apprenticedeliverysystem.core.order.mapper;

import at.ltb.apprenticedeliverysystem.core.order._persistence.OrderEntity;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderDetailDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.orderitem.OrderItemMapper;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.user.mapper.UserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import javax.inject.Inject;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {OrderItemMapper.class})
public abstract class OrderMapper {

    @Inject
    protected UserMapper userMapper;

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "orderDate", source = "groceryWorkingDay.date")
    @Mapping(target = "user", source = "user", qualifiedByName = "userOverview")
    public abstract OrderOverviewDTO mapOrderEntityToOverview(OrderEntity order);

    public abstract List<OrderOverviewDTO> mapOrderEntityToOverview(List<OrderEntity> orders);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "user", source = "user", qualifiedByName = "userDetail")
    public abstract OrderDetailDTO mapOrderEntityToDetail(OrderEntity order);

    @Named("userOverview")
    protected UserOverviewDTO userOverview(UserEntity user) {
        return userMapper.mapUserEntityToOverview(user);
    }

    @Named("userDetail")
    protected UserDetailDTO userDetail(UserEntity user) {
        return userMapper.mapUserEntityToDetail(user);
    }

}
