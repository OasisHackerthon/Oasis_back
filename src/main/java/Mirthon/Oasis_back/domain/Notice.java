package Mirthon.Oasis_back.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    private NoticeType noticeType;

    private String noticeContent;

    private LocalDateTime noticeDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fountainId")
    private Fountain fountain;
}
