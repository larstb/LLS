package at.ltb.apprenticedeliverysystem.configuration;

import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditAwareConfig implements AuditorAware<Long> {

    @Nonnull
    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(-1L);
    }
}
