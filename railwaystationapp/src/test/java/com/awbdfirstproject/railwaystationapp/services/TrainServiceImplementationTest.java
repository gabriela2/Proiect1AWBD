package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.domain.Train;
import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.exception.ResourceAlreadyExistsException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeDeletedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeUpdatedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplementationTest {

    @Mock
    TrainRepository trainRepository;
    @InjectMocks
    TrainServiceImplementation trainServiceImplementation;

    @Test
    void findByIdNotFound() {
        when(trainRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->trainServiceImplementation.findById(anyLong()));
        assertEquals("Resursa tren cu id-ul 0 nu poate fi gasita!", exception.getMessage());

    }


    @Test
    void findByIdFound() {
        Train train = new Train();
        when(trainRepository.findById(anyLong())).thenReturn(Optional.of(train));
        Train result= trainServiceImplementation.findById(anyLong());
        assertEquals(train,result);
    }

    @Test
    void findAll() {
        List<Train> trainList = new ArrayList<>();
        Train train = new Train();
        trainList.add(train);
        when(trainRepository.findAll()).thenReturn(trainList);
        List<Train> result = trainServiceImplementation.findAll();
        assertEquals(trainList,result);
    }

    @Test
    void saveNotFound() {
        Train train = new Train();
        train.setCode("9283");
        when(trainRepository.findByCode(anyString())).thenReturn(Optional.of(train));
        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class,()->trainServiceImplementation.save(train));
        assertEquals("In baza de date exista deja resursa 9283!",exception.getMessage());
    }

    @Test
    void saveFound() {
        when(trainRepository.findByCode(anyString())).thenReturn(Optional.empty());
        Train train = new Train();
        train.setId(293);
        train.setCode("12389");
        when(trainRepository.save(train)).thenReturn(train);
        Train result = trainServiceImplementation.save(train);
        assertEquals(train,result);
    }

    @Test
    void deleteByIdNotFound() {
        when(trainRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->trainServiceImplementation.deleteById(anyLong()));
        assertEquals("Resursa tren cu id-ul 0 nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void deleteByIdFoundJourneyNotNull() {
        Train train = new Train();
        List<Journey> journeys = new ArrayList<>();
        Journey journey = new Journey();
        journeys.add(journey);
        train.setJourneys(journeys);
        when(trainRepository.findById(anyLong())).thenReturn(Optional.of(train));
        ResourceCannotBeDeletedException exception = assertThrows(ResourceCannotBeDeletedException.class,()->trainServiceImplementation.deleteById(anyLong()));
        assertEquals("Resursa tren cu id-ul 0 nu poate fi stearsa!", exception.getMessage());
    }

    @Test
    void deleteByIdFoundJourneyNull() {
        Train train = new Train();
        List<Journey> journeys = new ArrayList<>();
        train.setJourneys(journeys);
        when(trainRepository.findById(anyLong())).thenReturn(Optional.of(train));
        trainServiceImplementation.deleteById(anyLong());
        verify(trainRepository).deleteById(anyLong());
    }


    @Test
    void updateByIdNotFound() {
        when(trainRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->trainServiceImplementation.update(anyLong(),new Train()));
        assertEquals("Resursa tren cu id-ul 0 nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void updateByIdFoundDifferentCode() {
        Train train = new Train();
        train.setCode("123");
        when(trainRepository.findById(anyLong())).thenReturn(Optional.of(train));
        Train updatedTrain = new Train();
        updatedTrain.setCode("321");
        ResourceCannotBeUpdatedException exception = assertThrows(ResourceCannotBeUpdatedException.class,()->trainServiceImplementation.update(anyLong(),updatedTrain));
        assertEquals("Resursa tren cu id-ul 0 nu poate fi actualizata!", exception.getMessage());
    }

    @Test
    void updateByIdFoundSameCode() {
        Train train = new Train();
        train.setCode("123");
        when(trainRepository.findById(anyLong())).thenReturn(Optional.of(train));
        Train updatedTrain = new Train();
        updatedTrain.setCode("123");
        trainServiceImplementation.update(anyLong(),updatedTrain);
        verify(trainRepository).save(train);
    }

    @Test
    void findByCodeNotFound() {
        when(trainRepository.findByCode(anyString())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->trainServiceImplementation.findByCode(anyString()));
        assertEquals("Resursa tren cu codul  nu poate fi gasita!", exception.getMessage());

    }

    @Test
    void findByCodeFound() {
        Train train = new Train();
        when(trainRepository.findByCode(anyString())).thenReturn(Optional.of(train));
        Train result= trainServiceImplementation.findByCode(anyString());
        assertEquals(train,result);
    }

    @Test
    void findAllByCode() {
        List<String> stringList = new ArrayList<>();
        stringList.add("obj");
        when(trainRepository.findAllCodes()).thenReturn(stringList);
        List<String> result = trainServiceImplementation.findAllByCode();
        assertEquals(stringList,result);
    }
}