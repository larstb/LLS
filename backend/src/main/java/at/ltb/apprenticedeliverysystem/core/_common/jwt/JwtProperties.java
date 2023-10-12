package at.ltb.apprenticedeliverysystem.core._common.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtProperties {

    @Getter
    private final String claimId = "id";

    @Getter
    private final String claimEmail = "email";

    @Getter
    private final String claimSubject = "userToken";

    @Getter
    @Value("${own.security.jwt.expires-in-minutes}")
    private int minutes;

    @Getter
    @Value("${own.security.jwt.secret}")
    private String secret;

    @Getter
    @Value("${own.security.jwt.issuer}")
    private String issuer;
}
