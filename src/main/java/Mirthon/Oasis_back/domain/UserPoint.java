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
public class UserPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPointId;

    private Long userPoint;

    private LocalDateTime pointDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fountainId")
    private Fountain fountain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
