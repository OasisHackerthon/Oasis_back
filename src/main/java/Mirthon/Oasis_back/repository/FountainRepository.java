package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.Fountain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FountainRepository extends JpaRepository<Fountain, Long> {
    public List<Fountain> findByUserId(Long userId);

    public Fountain findByUserIdAndFountainId(Long userId,Long fountainId);
}
