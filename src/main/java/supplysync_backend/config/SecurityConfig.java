package supplysync_backend.config;
 
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import org.springframework.http.HttpMethod;
 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
 
import org.springframework.security.web.SecurityFilterChain;
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 
import java.util.List;
 
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
 
        http
 
                .csrf(csrf -> csrf.disable())
 
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
 
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))
 
                .authorizeHttpRequests(auth -> auth
 
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
 
                        .requestMatchers(
                                "/api/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
 
                        // TEMPORARY
                        .anyRequest().permitAll()
 
                );
 
        return http.build();
    }
 
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
 
        CorsConfiguration configuration = new CorsConfiguration();
 
        configuration.setAllowedOrigins(
    List.of(
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "http://localhost:5176",
        "https://supply-sync-ai-frontend-pi.vercel.app"
    )
);
 
        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );
 
        configuration.setAllowedHeaders(
                List.of("*")
        );
 
        configuration.setAllowCredentials(true);
 
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
 
        source.registerCorsConfiguration("/**", configuration);
 
        return source;
    }
 
   
 
}