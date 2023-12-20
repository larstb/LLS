package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateUserPortalDTO(
        @NotBlank String id,
        @Min(0) Integer version,
        @NotBlank String firstname,
        @NotBlank String lastname,
        String status,
        String phoneNumber,
        String location,
        String iban,
        String paypalLink,
        @NotBlank String email,
        @NotNull Boolean enabled,
        @Size(min = 1) List<RoleEnum> roles) implements BaseRequest {
}
