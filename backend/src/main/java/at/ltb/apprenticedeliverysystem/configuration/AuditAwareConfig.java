package at.ltb.apprenticedeliverysystem.configuration;

import at.ltb.apprenticedeliverysystem.core._common.auth.CurrentUserService;
import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditAwareConfig implements AuditorAware<Long> {

    private final CurrentUserService currentUserService;

    public AuditAwareConfig(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @Nonnull
    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(this.currentUserService.getCurrentUserIdFromSecurity());
    }
}
