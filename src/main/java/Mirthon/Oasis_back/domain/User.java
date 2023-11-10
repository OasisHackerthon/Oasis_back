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
    private String userInviteCode; // 추천인 코드
    private String password;
    private String email;
    private String userPhoneNumber;
    private String userName;
    @Column(name = "user_x", nullable = false)
    private int user_x = 0;

    @Column(name = "user_y", nullable = false)
    private int user_y = 0;

    public User(String userName, String password, String email, Long kakaoId, String userInviteCode) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
        this.userInviteCode = userInviteCode;
    }
    public void updateKakaoId(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

}
