package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.MyItem;
import Mirthon.Oasis_back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyItemRepository extends JpaRepository<MyItem, Long> {

    public List<MyItem> findByItemUser(User user);


}

