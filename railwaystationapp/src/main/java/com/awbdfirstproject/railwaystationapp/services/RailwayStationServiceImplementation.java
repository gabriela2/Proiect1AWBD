package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Address;
import com.awbdfirstproject.railwaystationapp.domain.RailwayStation;
import com.awbdfirstproject.railwaystationapp.exception.ResourceAlreadyExistsException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeDeletedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeUpdatedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.RailwayStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RailwayStationServiceImplementation implements RailwayStationServiceInterface {
    @Autowired
    RailwayStationRepository railwayStationRepository;
    @Autowired
    AddressServiceInterface addressServiceInterface;

    @Override
    public List<RailwayStation> findAll() {
        log.info("Return all the railway stations from db.");
        return railwayStationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        RailwayStation railwayStation = railwayStationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("statie cu id-ul " + id));
        if (railwayStation.getArrivalJourneys().size() == 0 && railwayStation.getDepartureJourneys().size() == 0) {
            log.info("The railway station with id = " + id + "was deleted");
            railwayStationRepository.deleteById(id);
        } else {
            log.info("the railway station " + railwayStation.getName() + " cannot be deleted");
            throw new ResourceCannotBeDeletedException("statie cu id-ul " + id);
        }

    }

    @Override
    public RailwayStation save(RailwayStation newRailwayStation) {
        Optional<RailwayStation> railwayStation = railwayStationRepository.getByName(newRailwayStation.getName());
        if (railwayStation.isPresent()) {
            log.info("The railway station " + newRailwayStation.getName() + " already exists in the db.");
            throw new ResourceAlreadyExistsException("statie cu numele" + newRailwayStation.getName());
        } else {
            Optional<Address> address = addressServiceInterface.findByElements(newRailwayStation.getAddress().getNumber(), newRailwayStation.getAddress().getStreet(), newRailwayStation.getAddress().getCity(), newRailwayStation.getAddress().getDistrict(), newRailwayStation.getAddress().getZipcode());
            if (address.isPresent()) {
                log.info("The address already exists in the db.");
                throw new ResourceAlreadyExistsException("adresa");
            }
            Address savedAddress = addressServiceInterface.save(new Address(newRailwayStation.getAddress().getNumber(), newRailwayStation.getAddress().getStreet(), newRailwayStation.getAddress().getCity(), newRailwayStation.getAddress().getDistrict(), newRailwayStation.getAddress().getZipcode()));
            newRailwayStation.setAddress(savedAddress);
            log.info("The railway station " + newRailwayStation.getName() + " was saved");
            return railwayStationRepository.save(newRailwayStation);
        }
    }

    @Override
    public RailwayStation findById(Long id) {
        RailwayStation railwayStation = railwayStationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("statie cu id-ul " + id));
        log.info("Return the railway station with id " + id);
        return railwayStation;
    }

    @Override
    public void update(Long id, RailwayStation newRailwayStationDto) {
        RailwayStation existingRailwayStation = railwayStationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("statie cu id-ul " + id));
        if (existingRailwayStation.getAddress().getZipcode().equals(newRailwayStationDto.getAddress().getZipcode())) {
            existingRailwayStation.setName(newRailwayStationDto.getName());
            existingRailwayStation.setRailwayType(newRailwayStationDto.getRailwayType());
            Address address = existingRailwayStation.getAddress();
            address.setStreet(newRailwayStationDto.getAddress().getStreet());
            address.setNumber(newRailwayStationDto.getAddress().getNumber());
            addressServiceInterface.save(address);
            railwayStationRepository.save(existingRailwayStation);
            log.info("The railway station with id " + id + " was updated");
        } else {
            log.info("The railway station with id " + id + " cannot be updated");
            throw new ResourceCannotBeUpdatedException("statie cu id-ul " + id);
        }
    }

    @Override
    public RailwayStation findByName(String name) {
        RailwayStation railwayStation = railwayStationRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("statie cu numele " + name));
        log.info("Return the railway with name " + name);
        return railwayStation;
    }

    @Override
    public List<String> findAllByName() {
        log.info("Return the names of all railway stations");
        return railwayStationRepository.findAllNames();
    }


}
