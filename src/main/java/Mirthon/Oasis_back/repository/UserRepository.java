package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByKakaoId(Long kakaoId);
    boolean existsByEmail(String email); //중복 가입 방지
}
