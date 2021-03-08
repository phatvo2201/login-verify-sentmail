package com.example.demo.registration;


import com.example.demo.appUser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/registration")

public class Registration {

    private RegistrationService registrationService;

    @PostMapping
    public String regis(@RequestBody RegistrationRequest request ) throws IllegalAccessException {
        return registrationService.regis(request);

    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
