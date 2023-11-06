package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
