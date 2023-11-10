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
    private String phone_number;
    private String profile_image_url;
    private String user_grade;
    private String user_invite_code;
    private String user_name;
    private int user_x;
    private int user_y;


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
