package com.example.demo.appUser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collections;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {


    @SequenceGenerator(
            name="student_sequence",
            sequenceName="student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private long id;
    private String lastName;
    private  String password;
    private String firstName;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enable;

    public AppUser(long id, String lastName, String password, String firstName, String email, AppUserRole appUserRole) {
        this.id = id;
        this.lastName = lastName;
        this.password = password;
        this.firstName = firstName;
        this.email = email;
        this.appUserRole = appUserRole;
    }

    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String password,
                   AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @java.lang.Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());



        return Collections.singletonList(authority);
    }

    @java.lang.Override
    public java.lang.String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public java.lang.String getLastName() {
        return lastName;
    }



    @java.lang.Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @java.lang.Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @java.lang.Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @java.lang.Override
    public boolean isEnabled() {
        return enable;
    }
}
