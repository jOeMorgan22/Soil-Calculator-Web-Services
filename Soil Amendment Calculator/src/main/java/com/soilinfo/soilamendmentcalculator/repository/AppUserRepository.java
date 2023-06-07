package com.soilinfo.soilamendmentcalculator.repository;

import com.soilinfo.soilamendmentcalculator.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findAppUserById(Long id);
    Optional<AppUser> findAppUserByUsername(String username);

}
