package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;

import java.util.List;

public record CreateUserPortalDTO(String firstname,
                                  String status,
                                  String lastname,
                                  String email,
                                  String phoneNumber,
                                  String location,
                                  String iban,
                                  String paypalLink,
                                  Boolean enabled,
                                  List<RoleEnum> roles) implements BaseResponse {
}
