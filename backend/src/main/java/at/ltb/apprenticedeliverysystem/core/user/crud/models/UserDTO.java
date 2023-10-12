package at.ltb.apprenticedeliverysystem.core.user.crud.models;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank Long id,
        @NotBlank String firstname,
        @NotBlank String lastname,
        String status,
        @NotBlank String phoneNumber,
        @NotBlank String email,
        String location,
        @NotBlank String role,
        String iban,
        String paypalLink,
        @NotBlank boolean isActive
) implements BaseResponse {}
