package com.soilinfo.soilamendmentcalculator.config;

import com.soilinfo.soilamendmentcalculator.entity.AppUser;
import com.soilinfo.soilamendmentcalculator.repository.AppUserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AppUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(AppUserRepository userRepository,
                                   PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<AppUser> authUser = userRepository.findAppUserByUsername(authentication.getName());
        if(authUser.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }
        AppUser verifiedUser = authUser.get();
        if(passwordEncoder.matches(authentication.getCredentials().toString(), verifiedUser.getPassword())){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(authentication.getName(), null,
                    authorities);
        }else{
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
