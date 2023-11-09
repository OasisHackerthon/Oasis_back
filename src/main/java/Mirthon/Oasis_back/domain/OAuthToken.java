package Mirthon.Oasis_back.domain;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class OAuthToken {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
