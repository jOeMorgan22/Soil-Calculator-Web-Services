package com.soilinfo.soilamendmentcalculator.controller;

import com.soilinfo.soilamendmentcalculator.entity.AppUser;
import com.soilinfo.soilamendmentcalculator.requestobj.UserRequestDetails;
import com.soilinfo.soilamendmentcalculator.responseobj.UserResponseDetails;
import com.soilinfo.soilamendmentcalculator.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final AppUserService userService;

    public UserController(AppUserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDetails> userLogin(Authentication authentication){
        AppUser user = userService
                .getAppUserByUsername(authentication.getName());
        return new ResponseEntity<>(new UserResponseDetails(user.getId(), user.getUsername()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDetails> registerNewUser(@Valid @RequestBody UserRequestDetails userRequest){
        AppUser newUser = new AppUser(userRequest.username(), passwordEncoder.encode(userRequest.password()));
        userService.saveNewUser(newUser);
        return new ResponseEntity<>(new UserResponseDetails(newUser.getId(), newUser.getUsername()), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<HttpStatus> deleteUser(Authentication authentication){
        userService.deleteAppUser(userService
                .getAppUserByUsername(authentication.getName()).getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
