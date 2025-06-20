package pe.edu.upeu.turismospringboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.edu.upeu.turismospringboot.filter.JwtAuthenticationFilter;
import pe.edu.upeu.turismospringboot.filter.ChatbotAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ChatbotAuthenticationFilter chatbotAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(
                                        "/auth/**",
                                        "/doc/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/swagger-resources/**",
                                        "/webjars/**",
                                        "/filePerfil/**",
                                        "/general/**",
                                        "/chatbot/**"
                                ).permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/usuario/**").hasRole("USUARIO")
                                .requestMatchers("/emprendedor/**").hasRole("EMPRENDEDOR")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(chatbotAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
