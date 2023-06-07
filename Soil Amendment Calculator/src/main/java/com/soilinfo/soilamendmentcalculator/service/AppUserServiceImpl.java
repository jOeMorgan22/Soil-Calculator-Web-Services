package com.soilinfo.soilamendmentcalculator.service;

import com.soilinfo.soilamendmentcalculator.entity.AppUser;
import com.soilinfo.soilamendmentcalculator.exception.AppUserAlreadyExistsException;
import com.soilinfo.soilamendmentcalculator.exception.AppUserNotFoundException;
import com.soilinfo.soilamendmentcalculator.repository.AppUserRepository;
import com.soilinfo.soilamendmentcalculator.requestobj.UserRequestDetails;
import com.soilinfo.soilamendmentcalculator.responseobj.UserResponseDetails;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository userRepository;

    public AppUserServiceImpl(AppUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public AppUser getAppUserById(Long id) {
        Optional<AppUser> appUser = userRepository.findAppUserById(id);
        if(appUser.isPresent()){
            return appUser.get();
        } throw new AppUserNotFoundException(id);
    }

    @Override
    public AppUser getAppUserByUsername(String username) {
        Optional<AppUser> appUser = userRepository.findAppUserByUsername(username);
        if(appUser.isPresent()){
            return appUser.get();
        } throw new AppUserNotFoundException(username);
    }

    @Override
    public AppUser saveNewUser(AppUser newUser) {
        Optional<AppUser> currentUser = userRepository.findAppUserByUsername(newUser.getUsername());
        if(currentUser.isPresent()) {
            throw new AppUserAlreadyExistsException();
        }
        return userRepository.save(newUser);
    }

    @Override
    public void deleteAppUser(Long id) {
        userRepository.deleteById(id);
    }


}
