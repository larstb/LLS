package at.ltb.apprenticedeliverysystem.core.order.dto;

import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateOrderDTO(@NotNull PaymentTypeEnum paymentType,
                             @Size(min = 1) List<CreateOrderOrderItemDTO> orderedItems) implements BaseRequest {
}
