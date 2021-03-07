package com.example.demo.registration;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {

    private String firstName;
    private  String password;
    private String email;
    private String lastName;
}
