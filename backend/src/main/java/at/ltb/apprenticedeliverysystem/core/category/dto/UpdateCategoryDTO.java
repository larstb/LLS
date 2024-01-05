package at.ltb.apprenticedeliverysystem.core.category.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryDTO(
        @NotBlank String id,
        @Min(0) Integer version,
        @NotBlank String name,
        String description) implements BaseRequest
{ }
