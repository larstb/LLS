package at.ltb.apprenticedeliverysystem.core.category.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;

public record CategoryOverviewDTO(String id, String name, String description) implements BaseResponse
{ }
