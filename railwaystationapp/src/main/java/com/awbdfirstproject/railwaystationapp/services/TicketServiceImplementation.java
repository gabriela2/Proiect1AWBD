package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.domain.Ticket;
import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.domain.UserType;
import com.awbdfirstproject.railwaystationapp.exception.InsufficientFundsException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeDeletedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.exception.ResourseCannotBeSavedException;
import com.awbdfirstproject.railwaystationapp.repositories.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class TicketServiceImplementation implements TicketServiceInterface {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    JourneyServiceInterface journeyServiceInterface;
    @Autowired
    UserServiceInterface userServiceInterface;

    @Override
    @Transactional
    public Ticket save(Principal principal, long journeyId) {
        Journey journey = journeyServiceInterface.findById(journeyId);
        User user = userServiceInterface.findByEmail(principal.getName());
        Double finalPrice = computeFinalPrice(user, journey.getTicketPrice());
        if (finalPrice > user.getBalance()) {
            throw new InsufficientFundsException();
        } else {
            if (journey.getTickets().size() < journey.getTrain().getNumberOfSeats()) {
                userServiceInterface.patchBalance(user.getId(), -finalPrice);
                Ticket ticket = new Ticket(finalPrice, user, journey);
                log.info("The ticket with id " + ticket.getId() + " was saved.");
                return ticketRepository.save(ticket);
            } else {
                log.info("The ticket cannot be saved.");
                throw new ResourseCannotBeSavedException("bilet");
            }
        }
    }

    @Override
    @Transactional
    public void delete(Principal principal, long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException("bilet"));
        User user = userServiceInterface.findByEmail(principal.getName());
        if (ticket.getJourney().getDepartureTime().compareTo(new Timestamp(System.currentTimeMillis())) > 0) {
            userServiceInterface.patchBalance(user.getId(), ticket.getFinalPrice());
            ticketRepository.delete(ticket);
            log.info("The ticket with id " + ticketId + " was deleted.");
        } else {
            log.info("The ticket with id " + ticketId + " cannot be deleted.");
            throw new ResourceCannotBeDeletedException("bilet");
        }
    }

    private Double computeFinalPrice(User user, double ticketPrice) {
        if (user.getUserType() == UserType.STUDENT) {
            return ticketPrice * 0.5;
        } else if (user.getUserType() == UserType.RETIRED) {
            return ticketPrice * 0.25;
        } else if (user.getUserType() == UserType.SOCIAL) {
            return ticketPrice * 0.75;
        } else if (user.getUserType() == UserType.MILITARY) {
            return ticketPrice * 0;
        } else {
            return ticketPrice;
        }
    }


    @Override
    public List<Ticket> findTicketsForUserId(long userId) {
        log.info("Return all the tickets present in db for a specific user");
        return ticketRepository.findAllByUserId(userId);
    }

    @Override
    public List<Ticket> findTicketsForJourneyId(long journeyId) {
        log.info("Return all the tickets present in db for a specific journey");
        return ticketRepository.findAllByJourneyId(journeyId);
    }
}
