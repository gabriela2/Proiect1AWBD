package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.domain.Incident;
import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.services.IncidentServiceInterface;
import com.awbdfirstproject.railwaystationapp.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class IncidentController {
    @Autowired
    IncidentServiceInterface incidentServiceInterface;
    @Autowired
    UserServiceInterface userServiceInterface;

    @GetMapping("/incident/user/{userId}/journey/{journeyId}")
    public ModelAndView getIncidentsByUserForJourney(@PathVariable("userId") long userId, @PathVariable("journeyId") long journeyId) {
        ModelAndView modelAndView = new ModelAndView("incident");
        modelAndView.addObject("incidents", incidentServiceInterface.getIncidentByUserForJourney(userId, journeyId));
        modelAndView.addObject("journeyid", journeyId);
        return modelAndView;
    }

    @RequestMapping("/incident/delete/{id}")
    public String deleteIncident(@PathVariable long id) {
        Incident incident = incidentServiceInterface.findById(id);
        incidentServiceInterface.delete(incident);
        return "redirect:/incident/user/" + incident.getUser().getId() + "/journey/" + incident.getJourney().getId();
    }

    @GetMapping(path = "/incident/incident-form/{id}")
    public String incidentForm(@PathVariable long id, Model model) {
        model.addAttribute("incident", new Incident());
        model.addAttribute("journeyid", id);
        return "incident-form";
    }


    @RequestMapping("/incident/incident-form/{id}")
    public String register(@PathVariable long id, Principal principal, @Valid @ModelAttribute Incident incident) {
        incidentServiceInterface.save(incident, id, principal);
        User user = userServiceInterface.findByEmail(principal.getName());
        return "redirect:/incident/user/" + user.getId() + "/journey/" + id;
    }

    @GetMapping("/admin/incident")
    public ModelAndView getIncidentsByUserForJourney() {
        ModelAndView modelAndView = new ModelAndView("admin-incident");
        modelAndView.addObject("incidents", incidentServiceInterface.getAllIncidents());
        return modelAndView;
    }

    @GetMapping(path = "/admin/incident/update/{id}")
    public String incidentUpdate(@PathVariable long id, Model model) {
        model.addAttribute("incident", incidentServiceInterface.findById(id));
        return "incident-update";
    }


    @RequestMapping("/admin/incident/update/{id}")
    public String register(@PathVariable long id, @ModelAttribute Incident incident) {
        incidentServiceInterface.update(incident);
        return "redirect:/admin/incident";
    }
}
