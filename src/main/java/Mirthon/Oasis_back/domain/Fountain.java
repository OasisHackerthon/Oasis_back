package Mirthon.Oasis_back.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Fountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fountainId;
}
