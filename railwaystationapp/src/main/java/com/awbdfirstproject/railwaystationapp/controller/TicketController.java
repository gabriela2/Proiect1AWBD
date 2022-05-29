package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.services.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class TicketController {
    @Autowired
    TicketServiceInterface ticketServiceInterface;


    @RequestMapping("/ticket/{id}")
    public String saveTicket(@PathVariable long id, Principal principal) {
        ticketServiceInterface.save(principal, id);
        return "redirect:/profile";
    }

    @RequestMapping("/ticket/delete/{id}")
    public String deleteTicket(@PathVariable long id, Principal principal) {
        ticketServiceInterface.delete(principal, id);
        return "redirect:/profile";
    }

    @GetMapping("/admin/journey/view-tickets/{id}")
    public ModelAndView getTicketsForJourney(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("tickets-for-journey");
        modelAndView.addObject("tickets", ticketServiceInterface.findTicketsForJourneyId(id));
        return modelAndView;
    }
}
