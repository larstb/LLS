package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        @NotBlank String id,
        @NotBlank @Min(0) Integer version,
        @NotBlank String firstname,
        @NotBlank String lastname,
        String status,
        String phoneNumber,
        String location,
        String iban,
        String paypalLink,
        @NotBlank String email) implements BaseRequest {
}
