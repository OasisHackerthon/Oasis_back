package Mirthon.Oasis_back.controller;

import Mirthon.Oasis_back.service.MyitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myitem")
public class MyitemController {
    private final MyitemService myitemService;

    @Autowired
    public MyitemController(MyitemService myitemService) {
        this.myitemService = myitemService;
    }

    @GetMapping("/mypoint/{userId}")
    public int getmyCurrentPoint(@PathVariable Long userId){
       return myitemService.getCurrentUserPoints(userId);
    }
}
