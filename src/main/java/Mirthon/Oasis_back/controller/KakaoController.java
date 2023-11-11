package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.config.kakao.KakaoOAuth2;
import Mirthon.Oasis_back.domain.KakaoAuthRequest;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.service.KakaoLoginService;
import Mirthon.Oasis_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
public class KakaoController {
    private final KakaoLoginService kakaoLoginService;
    private final UserService userService;
    private final KakaoOAuth2 kakaoOAuth2;

    @Autowired
    public KakaoController(KakaoLoginService kakaoLoginService,
                           KakaoOAuth2 kakaoOAuth2,
                           UserService userService) {
        this.kakaoLoginService = kakaoLoginService;
        this.kakaoOAuth2 = kakaoOAuth2;
        this.userService =userService;
    }


    // 카카오 서버로부터 받은 인가 코드
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoLoginCallback(@RequestBody KakaoAuthRequest kakaoAuthRequest) {
        String code = kakaoAuthRequest.getCode();
        userService.kakaoLogin(code);
        User currentUser = userService.getCurrentUser();
        // 리액트에게 로그인 성공 여부를 응답할 수 있도록 원하는 데이터를 반환
        return "카카오 인증 완료 ";
    }
}
