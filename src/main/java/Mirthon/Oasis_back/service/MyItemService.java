package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.Item;
import Mirthon.Oasis_back.domain.MyItem;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.domain.UserPoint;
import Mirthon.Oasis_back.repository.ItemRepository;
import Mirthon.Oasis_back.repository.MyItemRepository;
import Mirthon.Oasis_back.repository.UserPointRepository;
import Mirthon.Oasis_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class MyItemService {

    private final UserPointRepository userPointRepository;
    private final MyItemRepository myItemRepository;
    private final UserRepository userRepository;

    private final ItemRepository itemRepository;



    public Long getUserUsedPoint(Long userId) {
        User user = userRepository.findById(userId).get();
        Long totalUsedPoints =0L;
        List<MyItem> myItems = myItemRepository.findByItemUser(user);
        for(MyItem item : myItems) {
            totalUsedPoints += item.getUsePoint();
        }
        return totalUsedPoints;

    }//사용한 포인트 전체 반환하는 함수

    public Long getUserPoint(Long userId) {
        User user = userRepository.findById(userId).get();
        Long userHavePoints = 0L;
        List<UserPoint> userPoints = userPointRepository.findByUser(user);
        for(UserPoint userPoint : userPoints) {
            userHavePoints += userPoint.getUserPoint();
        }
        return userHavePoints;
    }


    public Long getCurrentUserPoints(Long userId) {
        //1.적립포인트 가져온다
        Long userPoint = getUserPoint(userId);
        //2.사용포인트 가져온다
        Long userUsedPoint = getUserUsedPoint(userId);
        //3.뺀다
        Long currentPoint = userPoint - userUsedPoint;

        return currentPoint;
    }

    public List<MyItem> getMyItems(Long userId) {
        User user = userRepository.getById(userId);
        return myItemRepository.findByItemUser(user);
    }

    public MyItem saveMyItem(Long itemId, Long userId) {
        Item item = itemRepository.getById(itemId);
        User user = userRepository.getById(userId);
        MyItem myItem1 = new MyItem();
        myItem1.setItemUser(user);

        String dateString = LocalDateTime.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 문자열을 LocalDateTime으로 파싱
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        myItem1.setBuyDate(date);

        myItem1.setBuyDate(LocalDateTime.now());
        myItem1.setUsePoint(item.getItemPrice());

        return myItemRepository.save(myItem1);
    }


}
