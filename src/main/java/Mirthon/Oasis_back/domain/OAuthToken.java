package Mirthon.Oasis_back.domain;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class OAuthToken {

    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private int expiresIn;
    private String scope;
    private Long accessTokenExpiresIn;
}
