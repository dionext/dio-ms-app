package com.dionext.dioportal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    final static private String prefix = "";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(prefix + "/admin/**").authenticated()
                        .requestMatchers(prefix + "/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())
                //.formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
