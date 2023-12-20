package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;

import java.util.List;

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
        String email,
        Boolean enabled,
        List<RoleEnum> roles) implements BaseRequest {
}
