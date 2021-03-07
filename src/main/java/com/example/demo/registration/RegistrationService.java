package com.example.demo.registration;

import com.example.demo.appUser.AppUser;
import com.example.demo.appUser.AppUserRole;
import com.example.demo.appUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

public String regis(RegistrationRequest request) throws IllegalAccessException {

    boolean isValidEmail= emailValidator.test(request.getEmail());
    if (!isValidEmail){
        throw  new IllegalAccessException("email is not valid");
    }
    return appUserService.signUpUser( new AppUser(request.getLastName(),request.getPassword(),request.getFirstName(),request.getEmail(), AppUserRole.USER));
}
}
