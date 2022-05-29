package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Incident;
import com.awbdfirstproject.railwaystationapp.domain.IncidentStatus;
import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeDeletedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.IncidentRepository;
import com.awbdfirstproject.railwaystationapp.repositories.JourneyRepository;
import com.awbdfirstproject.railwaystationapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.model.IProcessingInstruction;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncidentServiceImplementationTest {

    @Mock
    IncidentRepository incidentRepository;

    @Mock
    JourneyServiceInterface journeyRepository;
    @Mock
    UserServiceInterface userRepository;

    @InjectMocks
    IncidentServiceImplementation incidentServiceImplementation;


    @Test
    void delete() {
        Incident incident = new Incident();
        incident.setId(1l);
        incidentServiceImplementation.delete(incident);
        verify(incidentRepository).delete(incident);
    }

    @Test
    void update() {
        Incident incident1 = new Incident();
        incident1.setStatus(IncidentStatus.CLOSED);
        Incident incident = new Incident();
        when(incidentRepository.getById(anyLong())).thenReturn(incident);
        when(incidentRepository.save(incident)).thenReturn(incident1);
        Incident result = incidentServiceImplementation.update(incident);
        assertEquals(result,incident1);
    }

    @Test
    void getIncidentByUserForJourney() {
        List<Incident> incidentList = new ArrayList<>();
        Incident incident = new Incident();
        incidentList.add(incident);
        when(incidentRepository.findAllByUserIdAndJourneyId(anyLong(),anyLong())).thenReturn(incidentList);
        List<Incident> result = incidentServiceImplementation.getIncidentByUserForJourney(anyLong(),anyLong());
        assertEquals(result,incidentList);
    }

    @Test
    void getAllIncidents() {
        List<Incident> incidentList = new ArrayList<>();
        Incident incident = new Incident();
        incidentList.add(incident);
        when(incidentRepository.findAll()).thenReturn(incidentList);
        List<Incident> result = incidentServiceImplementation.getAllIncidents();
        assertEquals(result,incidentList);
    }

    @Test
    void findByIdNotFound() {
        when(incidentRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class,()->incidentServiceImplementation.findById(1L));
        assertEquals(result.getMessage(),"Resursa incident cu id-ul 1 nu poate fi gasita!");
    }

    @Test
    void findByIdFound() {
        Incident incident = new Incident();
        incident.setId(1l);
        when(incidentRepository.findById(anyLong())).thenReturn(Optional.of(incident));
        Incident result = incidentServiceImplementation.findById(anyLong());
        assertEquals(result,incident);
    }

    @Test
    void save() {
        Incident incident = new Incident();
        Journey journey = new Journey();
        User user = new User();
        when(journeyRepository.findById(anyLong())).thenReturn(journey);
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        incident.setJourney(journey);
        incident.setUser(user);
        incident.setStatus(IncidentStatus.NEW);
        when(incidentRepository.save(incident)).thenReturn(incident);
        Principal Principal = new Principal() {
            @Override
            public String getName() {
                return "VALUE";
            }
        };
        incidentServiceImplementation.save(incident,anyLong(),Principal);


    }
}