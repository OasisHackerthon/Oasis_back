package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.domain.MyItem;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.domain.UserPoint;
import Mirthon.Oasis_back.repository.UserRepository;
import Mirthon.Oasis_back.service.MyItemService;
import Mirthon.Oasis_back.service.UserPointService;
import Mirthon.Oasis_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserPointService userPointService;

    private final MyItemService myitemService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, UserPointService userPointService, MyItemService myitemService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userPointService = userPointService;
        this.myitemService = myitemService;
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

    @GetMapping("/{kakaoId}")
    public Optional<User> getUserInfo(@PathVariable Long kakaoId){
        return userService.getUserInfo(kakaoId);

    }
    //포인트적립히스토리


    @GetMapping("/api/getUser/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userRepository.getById(userId);
    }

}
