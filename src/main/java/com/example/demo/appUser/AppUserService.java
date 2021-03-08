package com.example.demo.appUser;

import com.example.demo.registration.Token.ConfirmationToken;
import com.example.demo.registration.Token.ConfirmationTokenRepository;
import com.example.demo.registration.Token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

private final String USER_NOT_FOUND_MGS="user with email %s not found";
private  final AppUserRepository appUserRepository;
private final BCryptPasswordEncoder bCryptPasswordEncoder;
private final ConfirmationTokenRepository confirmationTokenRepository;
private final ConfirmationTokenService confirmationTokenService;
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
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser

        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);


        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
