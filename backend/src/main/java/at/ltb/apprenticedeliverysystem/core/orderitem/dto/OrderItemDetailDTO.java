package at.ltb.apprenticedeliverysystem.core.orderitem.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;

public record OrderItemDetailDTO(String id, Integer orderedQuantity,
                                 String note, Double oldProductPrice, ProductDetailDTO product) implements BaseResponse {
}
