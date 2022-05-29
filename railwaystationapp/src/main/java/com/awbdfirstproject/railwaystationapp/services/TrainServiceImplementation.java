package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Train;
import com.awbdfirstproject.railwaystationapp.exception.ResourceAlreadyExistsException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeDeletedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeUpdatedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.TrainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainServiceImplementation implements TrainServiceInterface {
    @Autowired
    TrainRepository trainRepository;


    @Override
    public Train findById(long id) {
        Train train = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("tren cu id-ul " + id));
        log.info("Return the train with id " + id);
        return train;
    }

    @Override
    public List<Train> findAll() {
        log.info("Return all the trains present in the db");
        return trainRepository.findAll();
    }

    @Override
    public Train save(Train newtrain) {
        Optional<Train> train = trainRepository.findByCode(newtrain.getCode());
        if (train.isPresent()) {
            log.info("The train is already present in the db.");
            throw new ResourceAlreadyExistsException(newtrain.getCode());
        }
        log.info("Train " + newtrain.getCode() + " was saved");
        return trainRepository.save(newtrain);
    }

    @Override
    public void deleteById(Long id) {
        Train train = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("tren cu id-ul " + id));
        if (train.getJourneys().isEmpty()) {
            log.info("The train with id = " + id + "was deleted");
            trainRepository.deleteById(id);
        } else {
            log.info("The train " + train.getCode() + " cannot be deleted");
            throw new ResourceCannotBeDeletedException("tren cu id-ul " + id);
        }
    }

    @Override
    public void update(Long id, Train updatedTrain) {
        Train train = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("tren cu id-ul " + id));

        if (updatedTrain.getCode().compareTo(train.getCode()) == 0) {
            train.setNumberOfSeats(updatedTrain.getNumberOfSeats());
            train.setTrainType(updatedTrain.getTrainType());
            train.setFuelType(updatedTrain.getFuelType());
            trainRepository.save(train);
            log.info("Train " + train.getCode() + " was updated");
        } else {
            throw new ResourceCannotBeUpdatedException("tren cu id-ul " + id);
        }

    }

    @Override
    public Train findByCode(String trainCode) {
        Train train = trainRepository.findByCode(trainCode).orElseThrow(() -> new ResourceNotFoundException("tren cu codul " + trainCode));
        log.info("Return the train with code " + trainCode);
        return train;
    }

    @Override
    public List<String> findAllByCode() {
        log.info("Return all the codes present in db for trains.");
        return trainRepository.findAllCodes();
    }
}
