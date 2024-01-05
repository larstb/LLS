package at.ltb.apprenticedeliverysystem.core.product.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateProductDTO(
        @NotBlank String name,
        @NotBlank String producer,
        @NotBlank String quantity,
        @Min(0) Double price,
        @NotBlank Boolean isActive,
        @NotBlank Boolean isChecked,
        @NotBlank String categoryId) implements BaseRequest {
}
