package at.ltb.apprenticedeliverysystem.core.category.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;

public record UpdateCategoryDTO(String id, Integer version, String name, String description) implements BaseRequest
{ }
