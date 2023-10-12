package at.ltb.apprenticedeliverysystem.core.user.crud.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPasswordProperties {

    @Getter
    @Value("${own.security.password.initial_password}")
    private String initialPassword;

}
