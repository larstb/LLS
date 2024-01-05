package at.ltb.apprenticedeliverysystem.core.order.dto;

import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import at.ltb.apprenticedeliverysystem.core.order._persistence.OrderStatusEnum;
import at.ltb.apprenticedeliverysystem.core.orderitem.dto.OrderItemDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;

import java.util.List;

public record OrderDetailDTO(String id,
                             UserDetailDTO user,
                             Boolean isPayed,
                             GroceryWorkingDayDetailDTO groceryWorkingDay,
                             PaymentTypeEnum paymentType,
                             List<OrderItemDetailDTO> orderItems,
                             OrderStatusEnum status) implements BaseResponse {
}
