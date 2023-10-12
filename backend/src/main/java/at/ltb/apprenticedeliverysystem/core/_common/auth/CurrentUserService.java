package at.ltb.apprenticedeliverysystem.core._common.auth;

import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserLoginException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentUserService {

    private static final Long SYSTEM_USER_ID = -1L;

    private final UserCrudRepository userRepository;

    public CurrentUserService(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getCurrentUser() {
        return getCurrentUserOptional().orElseThrow(() -> new UserLoginException("authentication doesn't work"));
    }

    public Long getCurrentUserIdFromSecurity() {
        Object currentUserPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUserPrincipal.getClass().equals(String.class) ? SYSTEM_USER_ID : (Long) currentUserPrincipal;
    }

    private Optional<UserEntity> getCurrentUserOptional() {
        Long userId = getCurrentUserIdFromSecurity();
        return userId == -1L ? Optional.empty() : userRepository.findActiveUserById(userId);
    }

}
