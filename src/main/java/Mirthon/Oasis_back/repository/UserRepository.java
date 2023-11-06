package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
