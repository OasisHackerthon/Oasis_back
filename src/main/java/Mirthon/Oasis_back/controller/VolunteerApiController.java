package Mirthon.Oasis_back.controller;


import Mirthon.Oasis_back.domain.Volunteer;
import Mirthon.Oasis_back.repository.VolunteerRepository;
import Mirthon.Oasis_back.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:8080"}, allowCredentials = "true", allowedHeaders = "*")
public class VolunteerApiController {
    private final VolunteerRepository volunteerRepository;

    private final VolunteerService volunteerService;


    @PostMapping("/api/createVolunteer")
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.createVolunteer(volunteer);
    }

    // Read
    @GetMapping("/api/getAllVolunteers")
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @GetMapping("/api/getWaterVolunteers")
    public List<Volunteer> getWaterVolunteers() {
       return volunteerService.getWaterVolunteers();
    }


    @GetMapping("/api/getVolunteer/{volunteerId}")
    public Volunteer getVolunteerById(@PathVariable Long volunteerId) {
        return volunteerService.getVolunteerById(volunteerId);
    }

    // Update
    @PutMapping("/api/updateVolunteer/{volunteerId}")
    public Volunteer updateVolunteer(@PathVariable Long id, @RequestBody Volunteer updatedVolunteer) {
        return volunteerService.updateVolunteer(id, updatedVolunteer);
    }

    // Delete
    @DeleteMapping("/api/deleteVolunteer/{volunteerId}")
    public void deleteVolunteer(@PathVariable Long volunteerId) {
        volunteerService.deleteVolunteer(volunteerId);
    }


}
