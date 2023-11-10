package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.domain.*;
import Mirthon.Oasis_back.repository.FountainRepository;
import Mirthon.Oasis_back.repository.NoticeRepository;
import Mirthon.Oasis_back.repository.UserPointRepository;
import Mirthon.Oasis_back.repository.UserRepository;
import Mirthon.Oasis_back.service.FountainService;
import Mirthon.Oasis_back.service.NoticeService;
import Mirthon.Oasis_back.service.UserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",  allowedHeaders = "*")
public class MainPageApiController {
    private final FountainRepository fountainRepository;

    private final FountainService fountainService;
    private final NoticeRepository noticeRepository;
    private final NoticeService noticeService;
    private final UserPointService userPointService;
    private final UserPointRepository userPointRepository;



    //userId를 이용해 해당 user의 fountain전체를 받는 api
    @GetMapping("/api/getAllWater/{userId}")
    public List<Fountain> getAllWater(@PathVariable("userId") Long userId) {

        return fountainService.getAllFountains(userId);
    }

    //userId를 이용해 해당 user의 특정 fountain객체를 받는 api
    @GetMapping("/api/getAllWater/{userId}/{fountainId}")
    public Fountain getWater(@PathVariable("userId") Long userId, @PathVariable("fountainId") Long fountainId) {
        return fountainService.getFountainByIds(userId,fountainId);
    }


    //회원가입 진행할때 해당 id의 fountain객체들 생성하는 api
    @GetMapping("/api/saveFountain/{userId}")
    public void saveFountain(@PathVariable("userId") Long userId) throws Exception {
        fountainService.readJsonAndSaveFountains("/root/Java/Seoul.json", userId);
    }


    //fountain_state값 변경하는 함수(오염 or 정상)
    @PostMapping("/api/changeFountainState/{userId}/{fountainId}")
    public Fountain changeFountainState(@PathVariable("userId") Long userId, @PathVariable("fountainId") Long fountainId) {
        Fountain fountain = fountainService.changeFountainState(userId,fountainId);
        return fountain;
    }

    //fountain_waterState값 변경하는 함수(단수 or 음수가능)
    @PostMapping("/api/changeFountainWaterState/{userId}/{fountainId}")
    public Fountain changeFountainWaterState(@PathVariable("userId") Long userId, @PathVariable("fountainId") Long fountainId) {
        Fountain fountain = fountainService.changeFountainWaterState(userId,fountainId);
        return fountain;
    }

    //오염 notice 생성,전체 noticeList반환
    @PostMapping("/api/makeNotice/{fountainId}")
    public List<Notice> makeNotice(@PathVariable Long fountainId) {
        return noticeService.createNotice(fountainId);
    }


    //오염 notice 삭제,전체 noticeList반환
    @PostMapping("/api/deleteNotice/{fountainId}")
    public List<Notice> deleteNotice(@PathVariable Long fountainId) {
        return noticeService.deleteNotice(fountainId);
    }

    //단수 notice 생성,전체 noticeList반환
    @PostMapping("/api/makeWaterNotice/{fountainId}")
    public List<Notice> makeWaterNotice(@PathVariable Long fountainId) {
        return noticeService.createWaterNotice(fountainId);
    }


    //단수 notice 삭제,전체 noticeList반환
    @PostMapping("/api/deleteWaterNotice/{fountainId}")
    public List<Notice> deleteWaterNotice(@PathVariable Long fountainId) {
        return noticeService.deleteWaterNotice(fountainId);
    }




    //userPoint 생성, fountain visited로변경,date기록
    @PostMapping("/api/createUserPoint/{userId}/{fountainId}")
    public UserPoint createUserPoint(@PathVariable("userId") Long userId, @PathVariable("fountainId") Long fountainId) {
        return userPointService.createUserPoint(userId,fountainId);
    }


    //해당하는 user의 userpoint 전부 삭제 / 삭제된 userpoint 정보 반환 / fountain의 visited는 변경x
    @PostMapping("/api/deleteUserPoint/{userId}")
    public List<UserPoint> deleteUserPoint(@PathVariable("userId") Long userId) {
        return userPointService.deleteUserPoint(userId);
    }

    //해당하는 user의 userpoint 정보 반환
    @GetMapping("/api/getUserPoint/{userId}")
    public List<UserPoint> getUserPoint(@PathVariable("userId") Long userId) {
        return userPointService.getAllUserPoints(userId);
    }
}
