package at.ltb.apprenticedeliverysystem.core.category.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;

public record CreateCategoryDTO(String name, String description) implements BaseRequest
{ }
