package Mirthon.Oasis_back.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RefreshToken {

    @Id
    @Column(nullable = false, columnDefinition = "varchar(200)")
    private String email;

    @Column(nullable = false)
    private String value;

    public void updateValue(String token) {
        this.value = token;
    }
}

