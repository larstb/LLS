package at.ltb.apprenticedeliverysystem.core.order.dto;

import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import at.ltb.apprenticedeliverysystem.core.order._persistence.OrderStatusEnum;
import at.ltb.apprenticedeliverysystem.core.orderitem.dto.OrderItemOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;

import java.time.LocalDate;
import java.util.List;

public record OrderOverviewDTO(String id,
                               UserOverviewDTO user,
                               Boolean isPayed,
                               LocalDate orderDate,
                               PaymentTypeEnum paymentType,
                               List<OrderItemOverviewDTO> orderItems,
                               OrderStatusEnum status) implements BaseResponse {
}
