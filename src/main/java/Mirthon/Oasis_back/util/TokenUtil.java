package Mirthon.Oasis_back.util;

import Mirthon.Oasis_back.domain.OAuthToken;
import Mirthon.Oasis_back.domain.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;

public class TokenUtil {
    private static final String SECRET_KEY = "WmGu0virUUzgbJUsZvQfTyJj7tlbjur3";


    public static boolean isTokenExpired(String token, String secretKey) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return false; // Token is valid
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return true; // Token is invalid
        }
    }
}
