package com.soilinfo.soilamendmentcalculator.config;

import com.soilinfo.soilamendmentcalculator.filters.JWTGenerationFilter;
import com.soilinfo.soilamendmentcalculator.filters.JWTValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final RSAKeyProperties rsaKeyProperties;

    public SecurityConfig(RSAKeyProperties rsaKeyProperties){
        this.rsaKeyProperties = rsaKeyProperties;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http

                .sessionManagement(session ->
                {
                    try {
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JWTValidationFilter(rsaKeyProperties), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTGenerationFilter(rsaKeyProperties), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/register", "/login")
                .permitAll()
                .requestMatchers(HttpMethod.DELETE, "/delete-account", "/reports/**")
                .authenticated()
                .requestMatchers(HttpMethod.GET, "/reports", "/reports/**")
                .authenticated()
                .requestMatchers(HttpMethod.POST, "/reports")
                .authenticated())
                .httpBasic(withDefaults());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });


        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
