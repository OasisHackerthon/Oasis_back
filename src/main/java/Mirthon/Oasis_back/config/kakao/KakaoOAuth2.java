package Mirthon.Oasis_back.config.kakao;

import Mirthon.Oasis_back.domain.OAuthToken;
import Mirthon.Oasis_back.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoOAuth2 {

    public KakaoUserInfo getUserInfo(String authorizedCode) {
        System.out.println("인가코드 : "+ authorizedCode);
        // 1. 인증코드 -> 액세스 토큰
        String accessToken = getAccessToken(authorizedCode);
        // 2. 액세스 토큰 -> 카카오 사용자 정보
        KakaoUserInfo userInfo = getUserInfoByToken(accessToken);

        return userInfo;
    }

    public String getAccessToken(String authorizedCode) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "bd9c7a5b1824856cb653477b896c35cc");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", authorizedCode);
        params.add("client_secret", "WmGu0virUUzgbJUsZvQfTyJj7tlbjur3");

        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoRequest,
                String.class
        );

        String tokenJson = response.getBody();
        JSONObject rjson = new JSONObject(tokenJson);
        // 리프레시 토큰도 얻어오기
        String accessToken = rjson.getString("access_token");
        String refreshToken = rjson.getString("refresh_token");
        System.out.println("액세스 토큰 : " + accessToken);
        System.out.println("리프레시 토큰 : " + refreshToken);

        // 만약 액세스 토큰이 만료되면 리프레시 토큰을 이용하여 새로운 액세스 토큰을 얻음
        if (isAccessTokenExpired(accessToken)) {
            accessToken = refreshAccessToken(refreshToken);
        }

        return accessToken;
    }

    private boolean isAccessTokenExpired(String accessToken) {
        String secretKey = "WmGu0virUUzgbJUsZvQfTyJj7tlbjur3";
        return TokenUtil.isTokenExpired(accessToken, secretKey);
    }

    private String refreshAccessToken(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", "bd9c7a5b1824856cb653477b896c35cc");
        params.add("refresh_token", refreshToken);
        params.add("client_secret", "WmGu0virUUzgbJUsZvQfTyJj7tlbjur3");

        HttpEntity<MultiValueMap<String, String>> refreshRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                refreshRequest,
                String.class
        );

        String tokenJson = response.getBody();
        JSONObject rjson = new JSONObject(tokenJson);

        // 갱신된 액세스 토큰 반환
        return rjson.getString("access_token");
    }

    private KakaoUserInfo getUserInfoByToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        JSONObject body = new JSONObject(response.getBody());
        Long id = body.getLong("id");
        String email = body.getJSONObject("kakao_account").getString("email");
        String nickname = body.getJSONObject("properties").getString("nickname");
        System.out.println("개별 정보: " + id + " " + email + " " + nickname);
        return new KakaoUserInfo(id, email, nickname);
    }
}