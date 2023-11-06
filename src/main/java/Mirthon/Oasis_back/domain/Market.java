package Mirthon.Oasis_back.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
