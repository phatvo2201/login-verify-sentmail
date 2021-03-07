package com.example.demo.registration.Token;


import com.example.demo.appUser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ComfirmationToken {


    @SequenceGenerator(
            name="confirmation_token_sequence",
            sequenceName="confirmation_token_sequence",
            allocationSize = 1
    )
    @javax.persistence.Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private long Id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public ComfirmationToken(String token, LocalDateTime createAt, LocalDateTime expiredAt, LocalDateTime confirmedAt,AppUser appUser) {
        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiredAt;
        this.confirmedAt = confirmedAt;
        this.appUser =appUser;
    }
}
