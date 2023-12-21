package at.ltb.apprenticedeliverysystem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] methods = {"GET", "POST", "PUT", "DELETE"};
        String[] origins = {"*"};
        registry.addMapping("/**").allowedOrigins(origins).allowedHeaders("*").allowedMethods(methods);
    }
}
