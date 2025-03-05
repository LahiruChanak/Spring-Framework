package lk.ijse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
/*
    In this code snippet, the security filter chain is configured to disable CSRF protection and to permit all
    requests to the "/customer" endpoint. The session management is also configured to use stateless sessions.
          * stateless -> doesn't remember the state of the client. (ex:- HTTP request)
          * stateful -> remember the state of the client. (ex:- web socket/socket programming)
*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(customizer ->
                customizer.requestMatchers("/customer").permitAll()).sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
