package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.config.kakao.KakaoOAuth2;
import Mirthon.Oasis_back.config.kakao.KakaoUserInfo;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.repository.UserRepository;
import Mirthon.Oasis_back.util.UserInviteCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class KakaoLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInviteCodeGenerator userInviteCodeGenerator;
    private final AuthenticationManager authenticationManager;
    private final KakaoOAuth2 kakaoOAuth2;
    private static final String ADMIN_TOKEN = "AAA";

    @Autowired
    public KakaoLoginService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       KakaoOAuth2 kakaoOAuth2,
                       UserInviteCodeGenerator userInviteCodeGenerator,
                       AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kakaoOAuth2 = kakaoOAuth2;
        this.userInviteCodeGenerator = userInviteCodeGenerator;
        this.authenticationManager = authenticationManager;
    }

    public void kakaoLogin(String authorizedCode) {
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickName();
        String email = userInfo.getEmail();

        String userName = nickname;
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        if (kakaoUser == null) {
            // 회원가입 로직
            String userInviteCode = userInviteCodeGenerator.generateReCode();
            kakaoUser = new User(userName, password, email, kakaoId, userInviteCode);
            userRepository.save(kakaoUser);
            } else {
            // 중복된 이메일 체크
            if (userRepository.existsByEmail(email)) {
                // 중복된 이메일이 있을 경우, 해당 이메일로 회원 찾기
                User sameUser = userRepository.findByEmail(email).orElseThrow(
                        () -> new IllegalArgumentException("해당 이메일로 회원을 찾을 수 없습니다.")
                );
                kakaoUser = sameUser;

                // Kakao ID 업데이트
                kakaoUser.updateKakaoId(kakaoId);
            }
        }
        // 강제 로그인 처리
        Authentication kakaoUsernamePW = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePW);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
