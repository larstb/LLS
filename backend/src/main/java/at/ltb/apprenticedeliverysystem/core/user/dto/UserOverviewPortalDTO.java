package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;

public record UserOverviewPortalDTO(String id,
                                    String firstname,
                                    String lastname,
                                    String email) implements BaseResponse {
}
