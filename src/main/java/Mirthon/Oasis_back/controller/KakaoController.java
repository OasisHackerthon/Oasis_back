package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.config.kakao.KakaoOAuth2;
import Mirthon.Oasis_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KakaoController {
    private final UserService userService;
    private final KakaoOAuth2 kakaoOAuth2;

    @Autowired
    public KakaoController(UserService userService,
                           KakaoOAuth2 kakaoOAuth2) {
        this.userService = userService;
        this.kakaoOAuth2 = kakaoOAuth2;
    }


    // 카카오 서버로부터 받은 인가 코드
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoLoginCallback(@RequestParam(value = "code",required = false) String code) {
        userService.kakaoLogin(code);
        System.out.println("인가코드 : "+ code);
        return "카카오 인증 완료";
    }
}
