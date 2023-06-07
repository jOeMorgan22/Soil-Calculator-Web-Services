package com.soilinfo.soilamendmentcalculator.config;

import com.soilinfo.soilamendmentcalculator.entity.AppUser;
import com.soilinfo.soilamendmentcalculator.repository.AppUserRepository;
import com.soilinfo.soilamendmentcalculator.service.AppUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository userRepository;

    public CustomUserDetailsService(AppUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepository.findAppUserByUsername(username);
        if(appUser.isEmpty()){
            throw new UsernameNotFoundException(username + " not found");
        }
        String password = appUser.get().getPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return User.withUsername(username)
                .password(password)
                .authorities(authorities)
                .disabled(false)
                .build();
    }
}
