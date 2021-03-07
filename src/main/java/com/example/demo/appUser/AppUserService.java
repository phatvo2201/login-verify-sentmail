package com.example.demo.appUser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

private final String USER_NOT_FOUND_MGS="user with email %s not found";
private  final AppUserRepository appUserRepository;
private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MGS,email)));
    }
    public String signUpUser(AppUser appUser) throws IllegalAccessException {
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if(userExist){
            throw  new IllegalAccessException("email is take");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        //sent email


        return "";
    }
}
