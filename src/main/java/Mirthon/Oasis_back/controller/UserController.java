package Mirthon.Oasis_back.controller;

import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{kakaoId}")
    public Optional<User> getUserInfo(@PathVariable Long kakaoId){
        return userService.getUserInfo(kakaoId);

    }

}
