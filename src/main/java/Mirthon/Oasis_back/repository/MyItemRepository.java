package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.MyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyItemRepository extends JpaRepository<MyItem, Long> {
}
