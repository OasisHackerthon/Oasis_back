package Mirthon.Oasis_back.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "kakao_id")
    private Long kakaoId;
    private String password;
    private String email;
    private String userPhoneNumber;
    private String username;

    public User(String username, String password, String email, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
    }
    public void updateKakoId(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

}
