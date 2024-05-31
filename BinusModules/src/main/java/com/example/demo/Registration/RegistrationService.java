package com.example.demo.Registration;

import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserRole;
import com.example.demo.AppUser.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final AppUserService appUserService;

    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public boolean register(RegistrationRequest request){
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
