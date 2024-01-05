package at.ltb.apprenticedeliverysystem.core.order.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;

public record CreateOrderOrderItemDTO(String productId, Integer orderedQuantity, String note) implements BaseRequest {
}
