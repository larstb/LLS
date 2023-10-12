package at.ltb.apprenticedeliverysystem.core.user.crud.models;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank String firstname,
        @NotBlank String lastname,
        String status,
        @NotBlank String phoneNumber,
        @NotBlank String email,
        String location,
        @NotBlank String role,
        String iban,
        @NotBlank String paypalLink,
        boolean isActive
) implements BaseRequest {}
