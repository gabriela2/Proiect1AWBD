package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.exception.*;
import com.awbdfirstproject.railwaystationapp.repositories.JourneyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;


@Service
@Slf4j
public class JourneyServiceImplementation implements JourneyServiceInterface {

    @Autowired
    JourneyRepository journeyRepository;


    @Override
    public Page<Journey> findPageSortingAfterDepartureTimeForCompanyId(int currentPage, int limitPage, long companyId) {
        Pageable pageable = PageRequest.of(currentPage - 1, limitPage, Sort.by("departureTime").ascending());
        log.info("Return all the journey for a specific company");
        return journeyRepository.findAllByCompanyId(companyId, pageable);
    }

    @Override
    public Page<Journey> findPageSortingAfterDepartureTimeForDepartureRailway(int currentPage, int limitPage, long departureRailwayStationId) {
        Pageable pageable = PageRequest.of(currentPage - 1, limitPage, Sort.by("departureTime").ascending());
        log.info("Return all the journey for a specific railway station");
        return journeyRepository.findAllByDepartureRailwayStationId(departureRailwayStationId, pageable);
    }

    @Override
    public Page<Journey> findPageSortingAfterDepartureTimeForArrivalRailway(int currentPage, int limitPage, long arrivalRailwayStationId) {
        Pageable pageable = PageRequest.of(currentPage - 1, limitPage, Sort.by("departureTime").ascending());
        log.info("Return all the journey for a specific railway station");
        return journeyRepository.findAllByArrivalRailwayStationId(arrivalRailwayStationId, pageable);
    }

    @Override
    public Page<Journey> findPageSortingAfterDepartureTimeForAdmin(int currentPage, int limitPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, limitPage, Sort.by("departureTime").ascending());
        log.info("Return all the journey for admin");
        return journeyRepository.findAll(pageable);

    }

    @Override
    public Page<Journey> findPageSortingAfterDepartureTimeForUsers(int currentPage, int limitPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, limitPage, Sort.by("departureTime").ascending());
        log.info("Return all the journey for a specific user");
        return journeyRepository.findByDepartureTimeGreaterThan(new Timestamp(System.currentTimeMillis()), pageable);
    }

    @Override
    public void deleteById(long id) {
        Journey journey = journeyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("calatorie cu id-ul " + id));
        if (journey.getTickets().size() == 0 && journey.getDepartureTime().compareTo(new Timestamp(System.currentTimeMillis())) > 0) {
            journeyRepository.delete(journey);
            log.info("Delete a specific railway station");
        } else {
            log.info("Specific railway station cannot be deleted");
            throw new ResourceCannotBeDeletedException("calatorie cu id-ul " + id);
        }


    }

    @Override
    public void update(Long id, Journey journey) {
        if (!(id == journey.getId())) {
            log.info("Specific railway station cannot be updated");
            throw new ResourceCannotBeUpdatedException("calatorie cu id-ul " + id);
        }
        System.out.println(journey.getDepartureTime());
        System.out.println(new Timestamp(System.currentTimeMillis()));
        Journey existingJourney = journeyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("calatorie cu id-ul " + id));
        if (existingJourney.getArrivalTime().compareTo(new Timestamp(System.currentTimeMillis())) > 0) {
            existingJourney.setMinuteLate(journey.getMinuteLate());
            existingJourney.setDistance(journey.getDistance());
            existingJourney.setTrain(journey.getTrain());
            existingJourney.setCompany(journey.getCompany());
            existingJourney.setTicketPrice(journey.getTicketPrice());
            existingJourney.setJourneyStatus(journey.getJourneyStatus());
            if (journey.getDepartureTime().compareTo(journey.getArrivalTime()) > 0) {
                log.info("Specific railway station cannot be updated");
                throw new ResourceCannotBeUpdatedException("calatorie cu id-ul" + id);
            }
            existingJourney.setDepartureTime(journey.getDepartureTime());
            existingJourney.setArrivalTime(journey.getArrivalTime());
            journeyRepository.save(existingJourney);
            log.info("Specific railway station was updated");
        } else {
            log.info("Specific railway station cannot be updated");
            throw new ResourceCannotBeUpdatedException("calatorie cu id-ul " + id);
        }


    }

    @Override
    public Journey save(Journey journey) {
        Optional<Journey> existingJourney = journeyRepository.findJourney(journey.getCompany().getId(), journey.getDepartureTime(),
                journey.getArrivalTime(), journey.getTrain().getId(), journey.getDepartureRailwayStation().getId(), journey.getArrivalRailwayStation().getId());
        if (!existingJourney.isEmpty()) {
            log.info("Specific railway station cannot be found");
            throw new ResourceAlreadyExistsException("calatorie");
        } else {
            if (journey.getDepartureRailwayStation() == journey.getArrivalRailwayStation()) {
                log.info("Specific railway station cannot be saved");
                throw new ResourseCannotBeSavedException("calatorie");
            }
            if (journey.getDepartureTime().compareTo(journey.getArrivalTime()) > 0 || new Timestamp(System.currentTimeMillis()).compareTo(journey.getDepartureTime()) > 0) {
                log.info("Specific railway station cannot be saved");
                throw new ResourseCannotBeSavedException("calatorie");
            }
            log.info("Specific railway station was saved");
            return journeyRepository.save(journey);
        }
    }

    @Override
    public Journey findById(long id) {
        Journey journey = journeyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("calatorie cu id-ul " + id));
        log.info("Return a specific railway station");
        return journey;
    }
}