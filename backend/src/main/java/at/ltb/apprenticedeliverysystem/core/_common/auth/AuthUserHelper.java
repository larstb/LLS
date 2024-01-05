package at.ltb.apprenticedeliverysystem.core._common.auth;

import at.ltb.apprenticedeliverysystem.configuration.security.JwtAuthConverter;
import at.ltb.apprenticedeliverysystem.core._common.auth.exception.JwtClaimException;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserQueryDSLRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthUserHelper {

    private final JwtAuthConverter jwtAuthConverter;

    private final UserQueryDSLRepository userQueryDSLRepository;

    public AuthUserHelper(JwtAuthConverter jwtAuthConverter, UserQueryDSLRepository userQueryDSLRepository) {
        this.jwtAuthConverter = jwtAuthConverter;
        this.userQueryDSLRepository = userQueryDSLRepository;
    }

    public String getCurrentUser() {
        Jwt t;
        try {
            t = (Jwt) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception e) {
            throw new JwtClaimException("could not parse token");
        }
        return jwtAuthConverter.getPrincipalId(t);
    }

    public UserEntity getCurrentUserEntity() {
        return userQueryDSLRepository.loadUserByKeyCloakReference(getCurrentUser());
    }
}
