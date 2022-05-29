package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Ticket;
import com.awbdfirstproject.railwaystationapp.repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplementationTest {

    @Mock
    TicketRepository ticketRepository;
    @InjectMocks
    TicketServiceImplementation ticketServiceImplementation;


    @Test
    void findTicketsForUserId() {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticketList.add(ticket);

        when(ticketRepository.findAllByUserId(anyLong())).thenReturn(ticketList);
        List<Ticket> result = ticketServiceImplementation.findTicketsForUserId(anyLong());
        assertEquals(ticketList,result);
    }

    @Test
    void findTicketsForJourneyId() {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticketList.add(ticket);

        when(ticketRepository.findAllByJourneyId(anyLong())).thenReturn(ticketList);
        List<Ticket> result = ticketServiceImplementation.findTicketsForJourneyId(anyLong());
        assertEquals(ticketList,result);
    }
}