package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Ticket;

import java.security.Principal;
import java.util.List;

public interface TicketServiceInterface {
    Ticket save(Principal userId, long journeyId);

    void delete(Principal userId, long journeyId);

    List<Ticket> findTicketsForUserId(long userId);

    List<Ticket> findTicketsForJourneyId(long journeyId);
}
