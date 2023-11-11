package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.Volunteer;
import Mirthon.Oasis_back.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;

    // Create
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    // Read
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public List<Volunteer> getWaterVolunteers() {
        List<Volunteer> all = volunteerRepository.findAll();
        List<Volunteer> water = new ArrayList<>();
        for(Volunteer volu : all) {
            if(volu.isWater()) {
                water.add(volu);
            }
        }
        return water;
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id).get();
    }

    // Update
    public Volunteer updateVolunteer(Long id, Volunteer updatedVolunteer) {
        Volunteer existingVolunteerOptional = volunteerRepository.findById(id).get();

        if (existingVolunteerOptional !=null) {
            Volunteer existingVolunteer = existingVolunteerOptional;

            // Update fields as needed
            existingVolunteer.setTitle(updatedVolunteer.getTitle());
            existingVolunteer.setDate(updatedVolunteer.getDate());
            existingVolunteer.setP(updatedVolunteer.getP());
            existingVolunteer.setMember(updatedVolunteer.getMember());
            existingVolunteer.setUrl(updatedVolunteer.getUrl());
            existingVolunteer.setCenter(updatedVolunteer.getCenter());

            return volunteerRepository.save(existingVolunteer);
        } else {
            // Handle not found case
            return null;
        }
    }

    // Delete
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}
