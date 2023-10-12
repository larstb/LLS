package at.ltb.apprenticedeliverysystem.configuration;

import at.ltb.apprenticedeliverysystem.core._common.jwt.JwtDecoder;
import at.ltb.apprenticedeliverysystem.core._common.jwt.JwtProperties;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;

    private final UserCrudRepository userRepository;

    private final JwtProperties properties;

    public JwtTokenFilter(JwtDecoder jwtDecoder, UserCrudRepository userRepository, JwtProperties properties) {
        this.jwtDecoder = jwtDecoder;
        this.userRepository = userRepository;
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtDecoder.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        UserEntity userDetails = userRepository
                .findActiveUserById(jwtDecoder.getClaimLong(token, properties.getClaimId()))
                .orElse(null);

        if (userDetails == null) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getId(),
                null,
                List.of(new SimpleGrantedAuthority(userDetails.getRole().getAuthority()))
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
