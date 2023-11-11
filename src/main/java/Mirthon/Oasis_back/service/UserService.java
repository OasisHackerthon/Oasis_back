package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.config.kakao.KakaoOAuth2;
import Mirthon.Oasis_back.config.kakao.KakaoUserInfo;
import Mirthon.Oasis_back.domain.KakaoUserDetails;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.repository.UserRepository;
import Mirthon.Oasis_back.util.UserInviteCodeGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInviteCodeGenerator userInviteCodeGenerator;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final KakaoOAuth2 kakaoOAuth2;
    @Value("${secret.key}")
    private String secretKey;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       KakaoOAuth2 kakaoOAuth2,
                       UserInviteCodeGenerator userInviteCodeGenerator){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kakaoOAuth2 = kakaoOAuth2;
        this.userInviteCodeGenerator = userInviteCodeGenerator;
    }

    @Transactional
    public void kakaoLogin(String authorizedCode) {
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickName();
        String email = userInfo.getEmail();

        String userName = nickname;
        String password = passwordEncoder.encode(kakaoId + secretKey);

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        if (kakaoUser == null) {
            // 중복된 이메일 체크
            if (userRepository.existsByEmail(email)) {
                // 중복된 이메일이 있을 경우, 해당 이메일로 회원 찾기
                User sameUser = userRepository.findByEmail(email).orElseThrow(
                        () -> new IllegalArgumentException("해당 이메일로 회원을 찾을 수 없습니다.")
                );
                kakaoUser = sameUser;

                // Kakao ID 업데이트
                kakaoUser.updateKakaoId(kakaoId);
            } else {
                // 회원가입 로직
                String userInviteCode = userInviteCodeGenerator.generateReCode();
                kakaoUser = new User(userName, password, email, kakaoId, userInviteCode);
                userRepository.save(kakaoUser);
            }
            // 로그인 처리
            Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(userName, password);
            Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    // 현재 로그인한 사용자
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public Optional<User> getUserInfo(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }
}