package Mirthon.Oasis_back.repository;


import Mirthon.Oasis_back.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
