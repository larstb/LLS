package at.ltb.apprenticedeliverysystem.core._common.jwt;

import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtClaimException;
import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtExpiresException;
import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtTokenException;
import at.ltb.apprenticedeliverysystem.core._common.jwt.exception.JwtUserException;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class JwtDecoder {

    private final JwtProperties properties;

    private final UserCrudRepository userRepository;

    public JwtDecoder(JwtProperties properties, UserCrudRepository userRepository) {
        this.properties = properties;
        this.userRepository = userRepository;
    }

    public boolean validate(String token) {
        DecodedJWT decodedJwt = decodeToken(token);
        validateClaims(token, decodeToken(token));

        Optional<UserEntity> userEntity = userRepository
                .findActiveUserById(decodedJwt.getClaim(properties.getClaimId()).asLong());

        if(userEntity.isEmpty()) {
            throw new JwtUserException("no user found for token: %s".formatted(token));
        }

        return true;
    }

    public String getClaimString(String token, String claimName) {
        DecodedJWT decodedJwt = decodeToken(token);
        validateClaims(token, decodeToken(token));

        String claim = decodedJwt.getClaim(claimName).asString();

        if (StringUtils.isEmpty(claim)) {
           throw new JwtClaimException("claim (string) is not readable token: %s and claimName: %s".formatted(token, claimName));
        }

        return claim;
    }

    public Long getClaimLong(String token, String claimName) {
        DecodedJWT decodedJwt = decodeToken(token);
        validateClaims(token, decodeToken(token));

        Long claim = decodedJwt.getClaim(claimName).asLong();

        if (Objects.isNull(claim)) {
            throw new JwtClaimException("claim (long) is not readable token: %s and claimName: %s".formatted(token, claimName));
        }

        return claim;
    }

    private void validateClaims(String token, DecodedJWT jwt) {
        if(Objects.isNull(jwt) || jwt.getClaims().keySet().isEmpty()) {
            throw new JwtTokenException("claims are not readable token: %s".formatted(token));
        }
    }

    private DecodedJWT decodeToken(String token) {
        try {
           return JWT.require(Algorithm.HMAC512(properties.getSecret()))
                    .withIssuer(properties.getIssuer())
                    .withSubject(properties.getClaimSubject())
                    .build()
                   .verify(token);
        } catch (JWTVerificationException e) {
            throw new JwtExpiresException("token is expired token: %s".formatted(token));
        }
    }
}
