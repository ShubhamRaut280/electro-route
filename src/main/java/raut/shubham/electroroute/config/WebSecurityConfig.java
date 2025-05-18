package raut.shubham.electroroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",        // OpenAPI JSON
                                "/swagger-ui/**",         // Swagger UI assets
                                "/swagger-ui.html",       // Swagger UI HTML
                                "/auth/**",
                                "/docs",
                                "/error",
                                "/actuator/**"
                                ,"/**"
                        ).permitAll()
                        .requestMatchers("/api/**").authenticated()
                )
                .httpBasic(basic -> basic.init(http));

        return http.build();
    }

}