package Mirthon.Oasis_back.controller;

import Mirthon.Oasis_back.service.MyitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myitem")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
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
