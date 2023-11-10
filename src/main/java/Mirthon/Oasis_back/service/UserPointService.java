package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.Fountain;
import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.domain.UserPoint;
import Mirthon.Oasis_back.repository.FountainRepository;
import Mirthon.Oasis_back.repository.UserPointRepository;
import Mirthon.Oasis_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class UserPointService {
    private final UserPointRepository userPointRepository;
    private final UserRepository userRepository;
    private final FountainRepository fountainRepository;

    private final FountainService fountainService;

    public UserPoint createUserPoint(Long userId, Long fountainId) {
        User user = userRepository.findById(userId).get();
        Fountain fountain = fountainRepository.findById(fountainId).get();

        UserPoint newUserPoint = new UserPoint();
        newUserPoint.setPointDate(LocalDateTime.now());
        newUserPoint.setUserPoint(100L);
        newUserPoint.setFountain(fountain);
        newUserPoint.setUser(user);

        //fountain update 추가 //visited,data,list에 추가, update
        fountain.setVisited(true);
        fountain.setVisitedDate(newUserPoint.getPointDate());
        fountainService.updateFountain(fountainId,fountain);

        return userPointRepository.save(newUserPoint);
    }

    public List<UserPoint> getAllUserPoints(Long userId) {
        User user = userRepository.findById(userId).get();
        return  userPointRepository.findByUser(user);
    }

    public UserPoint updateUserPoint(Long id, UserPoint updatedUserPoint) {
        if (userPointRepository.existsById(id)) {
            updatedUserPoint.setUserPointId(id);
            return userPointRepository.save(updatedUserPoint);
        }
        return null; // 또는 예외 처리를 수행할 수도 있습니다.
    }

    public List<UserPoint> deleteUserPoint(Long userId) {
        User user = userRepository.findById(userId).get();
        List<UserPoint> deletedUserpoint = userPointRepository.deleteByUser(user);
        return deletedUserpoint;
    }
}
