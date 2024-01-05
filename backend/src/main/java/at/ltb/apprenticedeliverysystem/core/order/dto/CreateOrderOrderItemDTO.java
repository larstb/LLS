package at.ltb.apprenticedeliverysystem.core.order.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateOrderOrderItemDTO(
        @NotBlank String productId,
        @Size(min = 1) Integer orderedQuantity,
        String note) implements BaseRequest {
}
