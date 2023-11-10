package Mirthon.Oasis_back.util;


import Mirthon.Oasis_back.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class UserInviteCodeGenerator {
    private static final int CODE_LENGTH = 8;

    private final UserRepository userRepository;

    public UserInviteCodeGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 추천인 코드 생성
    public String generateReCode() {
        String reCode;
        do {
            reCode = generateRandomCode();
        } while (isCodeAlreadyExists(reCode));

        return reCode;
    }


    // 추천인 코드를 문자열로 반환
    private String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[CODE_LENGTH];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    // 생성된 추천인 코드 중복 확인
    private boolean isCodeAlreadyExists(String userInviteCode) {
        return userRepository.existsByUserInviteCode(userInviteCode);
    }
}
