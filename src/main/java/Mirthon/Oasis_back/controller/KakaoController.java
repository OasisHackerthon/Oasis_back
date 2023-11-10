package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.config.kakao.KakaoOAuth2;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.service.KakaoLoginService;
import Mirthon.Oasis_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
    public @ResponseBody String kakaoLoginCallback(@RequestParam(value = "code",required = false) String code) {
        userService.kakaoLogin(code);
        User currentUser = userService.getCurrentUser();
        return "카카오 인증 완료 " + currentUser;
    }
}
