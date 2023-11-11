package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.config.kakao.KakaoOAuth2;
import Mirthon.Oasis_back.config.kakao.KakaoUserInfo;
import Mirthon.Oasis_back.domain.KakaoAuthRequest;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.dto.UserDTO;
import Mirthon.Oasis_back.service.KakaoLoginService;
import Mirthon.Oasis_back.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<UserDTO> kakaoLogin(@RequestParam String authorizedCode) {
        UserDTO userDTO = userService.kakaoLogin(authorizedCode);
        return ResponseEntity.ok(userDTO);
    }
}
