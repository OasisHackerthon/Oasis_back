package Mirthon.Oasis_back.controller;

import Mirthon.Oasis_back.domain.MyItem;
import Mirthon.Oasis_back.domain.UserPoint;
import Mirthon.Oasis_back.service.MyItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
public class MyItemController {
    private final MyItemService myitemService;

    @GetMapping("/api/getAllMyItems/{userId}")
    public List<MyItem> getMyItems(@PathVariable("userId") Long userId) {
        return myitemService.getMyItems(userId);
    }//포인트 사용 내역

    @GetMapping("/api/getUserPoints/{userId}")
    public Long getUserPoints(@PathVariable("userId") Long userId) {
        return myitemService.getCurrentUserPoints(userId);
    }//현재 유저가 가지고있는 point반환

    @PostMapping("/api/saveMyItem/{userId}/{itemId}")
    public void saveMyItem(@PathVariable("userId") Long userId,@PathVariable("itemId") Long itemId) {
        myitemService.saveMyItem(itemId,userId);
    }

}
