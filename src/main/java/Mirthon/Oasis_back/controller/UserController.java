package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.repository.UserRepository;
import Mirthon.Oasis_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // 현재 로그인한 사용자의 추천인 코드를 조회
    @GetMapping("/myInviteCode")
    public String getUserInviteCode() {
        User currentUser = userService.getCurrentUser();
        return (currentUser != null) ? currentUser.getUserInviteCode() : null;
    }

    // 추천인 코드 입력
    @PostMapping("/inviteCodeInput")
    public ResponseEntity<String> inputInviteCode() {
        // 구현해야됨
        return ResponseEntity.badRequest().body("사용자가 인증되지 않았습니다.");
    }
}
