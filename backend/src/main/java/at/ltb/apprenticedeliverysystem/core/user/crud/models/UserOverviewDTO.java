package at.ltb.apprenticedeliverysystem.core.user.crud.models;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;

public record UserOverviewDTO(
        Long id,
        String firstname,
        String lastname,
        String email,
        String role,
        boolean isActive
) implements BaseResponse {}
