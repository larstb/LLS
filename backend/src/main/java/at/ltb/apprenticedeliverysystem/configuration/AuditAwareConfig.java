package at.ltb.apprenticedeliverysystem.configuration;

import at.ltb.apprenticedeliverysystem.core._common.auth.AuthUserHelper;
import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@Transactional
public class AuditAwareConfig implements AuditorAware<String> {

    private final AuthUserHelper authUserHelper;

    public AuditAwareConfig(AuthUserHelper authUserHelper) {
        this.authUserHelper = authUserHelper;
    }

    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(authUserHelper.getCurrentUser());
    }
}
