package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Incident;

import java.security.Principal;
import java.util.List;

public interface IncidentServiceInterface {
    void delete(Incident incident);

    Incident update(Incident incident);

    List<Incident> getIncidentByUserForJourney(long userId, long journeyId);

    List<Incident> getAllIncidents();

    Incident findById(long id);

    void save(Incident incident, long journeyId, Principal principal);
}
