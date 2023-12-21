package at.ltb.apprenticedeliverysystem.configuration.security;

import at.ltb.apprenticedeliverysystem.core._common.auth.exception.JwtClaimException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtAuthConverter
        implements Converter<Jwt, AbstractAuthenticationToken> {

    @Value("${own.jwt.auth.converter.resource-client}")
    private String resourceClient;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        return new JwtAuthenticationToken(jwt, extractRoles(jwt), getPrincipalId(jwt));
    }

    public String getPrincipalId(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        return jwt.getClaim(claimName);
    }

    private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> mapRoles;
        List<String> roles;

        if(!resourceAccess.containsKey(resourceClient)) {
            throw new JwtClaimException("can not read jwt");
        }

        if (resourceAccess.get(resourceClient) instanceof Map<?, ?>) {
            mapRoles = (Map<String, Object>) resourceAccess.get(resourceClient);
        } else {
            throw new JwtClaimException("can not read jwt");
        }

        if(Objects.isNull(mapRoles) || mapRoles.entrySet().isEmpty()) {
            throw new JwtClaimException("can not read jwt");
        }

        if (mapRoles.get("roles") instanceof List<?>) {
            roles = (List<String>) mapRoles.get("roles");
        } else {
            throw new JwtClaimException("can not read jwt");
        }

        return roles.stream()
                .map(o -> new SimpleGrantedAuthority("ROLE_" + o.toString().replace("[", "").replace("]", "")))
                .collect(Collectors.toSet());
    }
}
