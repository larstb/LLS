package at.ltb.apprenticedeliverysystem.core.product.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryDetailDTO;

public record ProductDetailDTO(String id, Integer version, String name, String producer, String quantity,
                               Double price, Boolean isActive, Boolean isChecked, CategoryDetailDTO category) implements BaseResponse {
}
