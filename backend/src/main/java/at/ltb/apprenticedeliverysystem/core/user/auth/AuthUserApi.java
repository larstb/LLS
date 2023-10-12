package at.ltb.apprenticedeliverysystem.core.user.auth;

import at.ltb.apprenticedeliverysystem.core._common.auth.CurrentUserService;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AuthUserApi {

    private final CurrentUserService currentUserService;

    public AuthUserApi(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @GetMapping(value = "/current")
    public AuthenticatedUserResponse getCurrentAuthUser() {
        UserEntity user = currentUserService.getCurrentUser();
        return new AuthenticatedUserResponse(
                user.getFirstname(),
                user.getLastname(),
                List.of(user.getRole().getAuthority())
        );
    }
}
