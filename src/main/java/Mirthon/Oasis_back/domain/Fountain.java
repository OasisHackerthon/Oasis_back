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
public class Fountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fountainId;

    private String fountainName;

    private Double location_x;

    private Double location_y;

    private String fountainAddress;

    private String foundtainDetail;

    private FountainState fountainState;

    private FountainWaterState fountainWaterState;

    private boolean visited;

    private LocalDateTime visitedDate;

    private Long userId;

    @JsonIgnore
    @OneToMany(mappedBy = "fountain" , cascade = CascadeType.ALL)
    private List<Notice> noticeList;

    @JsonIgnore
    @OneToMany(mappedBy = "fountain" , cascade = CascadeType.ALL)
    private List<UserPoint> userPoints;
}
