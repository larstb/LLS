package at.ltb.apprenticedeliverysystem.core.category.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;

public record CategoryDetailDTO(String id, Integer version, String name, String description) implements BaseResponse
{ }
