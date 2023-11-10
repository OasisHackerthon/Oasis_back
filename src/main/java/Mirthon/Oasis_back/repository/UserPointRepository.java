package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.User;
import Mirthon.Oasis_back.domain.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPointRepository extends JpaRepository<UserPoint, Long> {
    public List<UserPoint> findByUser(User user);

    public List<UserPoint> deleteByUser(User user);
}
