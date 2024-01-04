package at.ltb.apprenticedeliverysystem.core.product.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;

public record UpdateProductDTO(String id, Integer version, String name, String producer, String quantity,
                               Double price, Boolean isActive, Boolean isChecked, String categoryId) implements BaseRequest {
}
