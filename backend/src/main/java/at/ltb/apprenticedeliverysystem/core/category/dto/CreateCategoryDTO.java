package at.ltb.apprenticedeliverysystem.core.category.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(@NotBlank String name, String description) implements BaseRequest
{ }
