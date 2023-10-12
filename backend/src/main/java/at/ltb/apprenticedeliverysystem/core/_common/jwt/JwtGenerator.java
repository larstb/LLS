package at.ltb.apprenticedeliverysystem.core._common.jwt;

import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtGenerator {

    private final JwtProperties properties;

    public JwtGenerator(JwtProperties properties) {
        this.properties = properties;
    }

    public String generate(UserEntity user) {
        return JWT.create()
                .withClaim(properties.getClaimId(), user.getId())
                .withClaim(properties.getClaimEmail(), user.getEmail())
                .withSubject(properties.getClaimSubject())
                .withIssuer(properties.getIssuer())
                .withJWTId(UUID.randomUUID().toString())
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plus(properties.getMinutes(), ChronoUnit.MINUTES)))
                .sign(Algorithm.HMAC512(properties.getSecret()));
    }

}
