package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.MyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyItemRepository extends JpaRepository<MyItem, Long> {

    List<MyItem> findByUserId(Long userId);

    @Query("SELECT COALESCE(SUM(m.usePoint), 0) FROM MyItem m WHERE m.userId = :userId")
    int findTotalUserPointByUserId(@Param("userId") Long userId);
}

