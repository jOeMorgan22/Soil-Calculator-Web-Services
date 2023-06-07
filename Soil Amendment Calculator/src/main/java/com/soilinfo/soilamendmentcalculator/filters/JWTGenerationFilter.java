package com.soilinfo.soilamendmentcalculator.filters;

import com.soilinfo.soilamendmentcalculator.config.RSAKeyProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;


public class JWTGenerationFilter extends OncePerRequestFilter {

    private final RSAKeyProperties rsaKeyProperties;

    public JWTGenerationFilter(RSAKeyProperties rsaKeyProperties){
        this.rsaKeyProperties = rsaKeyProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecretKey secretKey = Keys
                .hmacShaKeyFor(rsaKeyProperties
                        .publicKey()
                        .toString()
                        .getBytes(StandardCharsets.UTF_8)
                );
        if(null != authentication){
            String jwt = Jwts
                    .builder()
                    .setIssuer("self")
                    .setSubject("JWT")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 300000))
                    .signWith(secretKey).compact();
            response.addHeader("Authorization", jwt);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection){
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority authority : collection){
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

}
