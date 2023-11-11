package Mirthon.Oasis_back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class MyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myItemId;

    private LocalDateTime buyDate;

    private Long usePoint; //가격


    //fixMe: 필요한 기능일때 주석해제 필요 (현재는 오류나서 주석처리)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User itemUser;

    @JsonIgnore
    @OneToMany(mappedBy = "myItem" , cascade = CascadeType.ALL)
    private List<Item> items;
}
