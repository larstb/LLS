package at.ltb.apprenticedeliverysystem.core.product.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductDTO(
        @NotBlank String id,
        @Min(0) Integer version,
        @NotBlank String name,
        @NotBlank String producer,
        @NotBlank String quantity,
        @Min(0) Double price,
        @NotNull Boolean isActive,
        @NotNull Boolean isChecked,
        @NotBlank String categoryId) implements BaseRequest {
}
