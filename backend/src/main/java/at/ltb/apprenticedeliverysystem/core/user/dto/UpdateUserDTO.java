package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;

public record UpdateUserDTO(
        String id,
        Integer version,
        String firstname,
        String lastname,
        String status,
        String phoneNumber,
        String location,
        String iban,
        String paypalLink,
        String email) implements BaseRequest {
}
