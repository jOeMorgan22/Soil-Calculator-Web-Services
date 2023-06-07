package com.soilinfo.soilamendmentcalculator.service;

import com.soilinfo.soilamendmentcalculator.entity.AppUser;
import com.soilinfo.soilamendmentcalculator.requestobj.UserRequestDetails;
import com.soilinfo.soilamendmentcalculator.responseobj.UserResponseDetails;

public interface AppUserService {

    AppUser getAppUserById(Long id);
    AppUser getAppUserByUsername(String username);
    AppUser saveNewUser(AppUser appuser);
    void deleteAppUser(Long id);



}

