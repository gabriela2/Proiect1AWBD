package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Incident;
import com.awbdfirstproject.railwaystationapp.domain.IncidentStatus;
import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.IncidentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
public class IncidentServiceImplementation implements IncidentServiceInterface {

    @Autowired
    IncidentRepository incidentRepository;
    @Autowired
    UserServiceInterface userServiceInterface;
    @Autowired
    JourneyServiceInterface journeyServiceInterface;


    @Override
    public void delete(Incident incident) {
        log.info("Delete incident with id " + incident.getId());
        incidentRepository.delete(incident);
    }

    @Override
    public Incident update(Incident incident) {
        log.info("Get incident from db");
        Incident existingIncident = incidentRepository.getById(incident.getId());
        log.info("Set status for incident");
        existingIncident.setStatus(incident.getStatus());
        return incidentRepository.save(existingIncident);
    }

    @Override
    public List<Incident> getIncidentByUserForJourney(long userId, long journeyId) {
        log.info("Return incidents raised by userid=" + userId + " for journeyid=" + journeyId);
        return incidentRepository.findAllByUserIdAndJourneyId(userId, journeyId);
    }

    @Override
    public List<Incident> getAllIncidents() {
        log.info("Return all the incidents from db");
        return incidentRepository.findAll();
    }

    @Override
    public Incident findById(long id) {
        log.info("Find incident with id " + id);
        Incident incident = incidentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("incident cu id-ul " + id));
        return incident;
    }

    @Override
    public void save(Incident incident, long journeyId, Principal principal) {
        Journey journey = journeyServiceInterface.findById(journeyId);
        User user = userServiceInterface.findByEmail(principal.getName());
        incident.setJourney(journey);
        incident.setUser(user);
        incident.setStatus(IncidentStatus.NEW);
        log.info("Save incident");
        incidentRepository.save(incident);
    }
}
