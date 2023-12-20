package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateUserPortalDTO(@NotBlank String firstname,
                                  String status,
                                  @NotBlank String lastname,
                                  @NotBlank @Email String email,
                                  String phoneNumber,
                                  String location,
                                  String iban,
                                  String paypalLink,
                                  @NotNull Boolean enabled,
                                  @Size(min = 1) List<RoleEnum> roles) implements BaseResponse {
}
