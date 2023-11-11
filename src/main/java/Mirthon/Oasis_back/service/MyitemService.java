package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.MyItem;
import Mirthon.Oasis_back.domain.UserPoint;
import Mirthon.Oasis_back.repository.MyItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyitemService {

    private final UserPointService userPointService;
    private final MyItemRepository myItemRepository;

    @Autowired
    public MyitemService(UserPointService userPointService, MyItemRepository myItemRepository) {
        this.userPointService = userPointService;
        this.myItemRepository = myItemRepository;
    }


    public List<MyItem> getUserUsepoint(Long userId) {

        return  myItemRepository.findByUserId(userId);}


    public int getCurrentUserPoints(Long userId) {
        //1.적립포인트 가져온다
        List<UserPoint> pluspoint = userPointService.getAllUserPoints(userId);
        int sumPlus = pluspoint.size() * 100;
        //2.사용포인트 가져온다
        int sumMinus = myItemRepository.findTotalUserPointByUserId(userId);
        //3.뺀다
        int currentPoint = sumPlus-sumMinus;

        return currentPoint;
    }

}
