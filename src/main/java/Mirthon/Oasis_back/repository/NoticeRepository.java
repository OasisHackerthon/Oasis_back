package Mirthon.Oasis_back.repository;


import Mirthon.Oasis_back.domain.Fountain;
import Mirthon.Oasis_back.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    public List<Notice> findByFountain(Fountain fountain);
}
