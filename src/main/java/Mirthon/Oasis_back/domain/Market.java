package Mirthon.Oasis_back.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketId;
}
