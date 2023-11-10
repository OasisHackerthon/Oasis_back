package Mirthon.Oasis_back.util;

import io.jsonwebtoken.*;

import java.util.Date;
public class TokenUtil {
    private static final String SECRET_KEY = "WmGu0virUUzgbJUsZvQfTyJj7tlbjur3"; // 사용자의 시크릿 키로 교체해야 합니다.

    public static boolean isTokenExpired(String token, String secretKey) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return false; // Token is valid
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return true; // Token is invalid
        }
    }
}
