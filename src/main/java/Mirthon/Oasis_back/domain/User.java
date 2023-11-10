package Mirthon.Oasis_back.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    private String email;
    private String userPhoneNumber;

    private String userName;

    private String userGrade;

    private int user_x;
    private int user_y;

    private String profileImageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<UserPoint> userPoints;

    @JsonIgnore
    @OneToMany(mappedBy = "itemUser" , cascade = CascadeType.ALL)
    private List<MyItem> myItems;
}