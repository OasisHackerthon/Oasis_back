package Mirthon.Oasis_back.repository;

import Mirthon.Oasis_back.domain.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository <Volunteer ,Long> {
}
