package Mirthon.Oasis_back.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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
