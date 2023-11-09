package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.config.kakao.KakaoOAuth2;
import Mirthon.Oasis_back.config.kakao.KakaoUserInfo;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoOAuth2 kakaoOAuth2;
    private static final String ADMIN_TOKEN = "AAA";

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       KakaoOAuth2 kakaoOAuth2
                       ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kakaoOAuth2 = kakaoOAuth2;
    }

    public void kakaoLogin(String authorizedCode) {
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickName();
        String email = userInfo.getEmail();

        String username = nickname;
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        if (kakaoUser == null) {
            User sameUser = null;
            // kakaoEmail이 이미 가입된 Member인 경우, kakaoId만 업데이트 처리
            if (userRepository.existsByEmail(email)) {
                sameUser = userRepository.findByEmail(email).orElseThrow(
                        () -> new IllegalArgumentException("해당 Kakao email로 회원을 찾을 수 없습니다.")
                );
                kakaoUser = sameUser;
                kakaoUser.updateKakoId(kakaoId);
            } else {
                // 회원가입 로직
                kakaoUser = new User(username, password, email, kakaoId);
                userRepository.save(kakaoUser);
            }
        }
        // 로그인 처리
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

//        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
