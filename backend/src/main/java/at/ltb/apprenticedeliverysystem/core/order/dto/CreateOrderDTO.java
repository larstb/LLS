package at.ltb.apprenticedeliverysystem.core.order.dto;

import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;

import java.util.List;

public record CreateOrderDTO(PaymentTypeEnum paymentType,
                             List<CreateOrderOrderItemDTO> orderedItems) implements BaseRequest {
}
