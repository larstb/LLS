package at.ltb.apprenticedeliverysystem.core.product.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;

public record ProductOverviewDTO(String id, String name, Double price, Boolean isActive,
                                 Boolean isChecked, String categoryName) implements BaseResponse {
}
