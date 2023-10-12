package at.ltb.apprenticedeliverysystem.core.user.auth;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;

import java.util.List;

public record AuthenticatedUserResponse(String firstname,
                                        String lastname,
                                        List<String> role) implements BaseResponse {
}
