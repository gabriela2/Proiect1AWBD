package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.dto.JourneyDto;
import com.awbdfirstproject.railwaystationapp.mapper.JourneyMapper;
import com.awbdfirstproject.railwaystationapp.services.CompanyServiceInterface;
import com.awbdfirstproject.railwaystationapp.services.JourneyServiceInterface;
import com.awbdfirstproject.railwaystationapp.services.RailwayStationServiceInterface;
import com.awbdfirstproject.railwaystationapp.services.TrainServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class JourneyController {
    @Autowired
    JourneyServiceInterface journeyServiceInterface;
    @Autowired
    JourneyMapper journeyMapper;
    @Autowired
    TrainServiceInterface trainServiceInterface;
    @Autowired
    CompanyServiceInterface companyServiceInterface;
    @Autowired
    RailwayStationServiceInterface railwayStationServiceInterface;


    @GetMapping("/admin/journey/journey-form")
    public ModelAndView journeyForm() {
        ModelAndView modelAndView = new ModelAndView("journey-form");
        modelAndView.addObject("journey", new JourneyDto());
        modelAndView.addObject("trains", trainServiceInterface.findAllByCode());
        modelAndView.addObject("companies", companyServiceInterface.findAllByName());
        modelAndView.addObject("railwaystations", railwayStationServiceInterface.findAllByName());
        return modelAndView;
    }

    @PostMapping("/admin/journey/journey-form")
    public String create(@Valid @ModelAttribute JourneyDto journeyDto) {
        journeyServiceInterface.save(journeyMapper.journeyDtoToJourney(journeyDto));
        return "redirect:/admin/journey";
    }

    @GetMapping("/admin/journey")
    public ModelAndView getJourneysForAdmin(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);
        Page<Journey> journeyPage = journeyServiceInterface.findPageSortingAfterDepartureTimeForAdmin(currentPage, pageSize);
        ModelAndView modelAndView = new ModelAndView("admin-journey");
        modelAndView.addObject("journeyPage", journeyPage);
        return modelAndView;
    }

    @RequestMapping("/admin/journey/delete/{id}")
    public String deleteJourney(@PathVariable long id) {
        journeyServiceInterface.deleteById(id);
        return "redirect:/admin/journey";
    }

    @GetMapping("/admin/journey/update/{id}")
    public ModelAndView update(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("journey-update");
        modelAndView.addObject("journey", journeyMapper.journeyToJourneyDto(journeyServiceInterface.findById(id)));
        modelAndView.addObject("trains", trainServiceInterface.findAllByCode());
        modelAndView.addObject("companies", companyServiceInterface.findAllByName());
        return modelAndView;
    }

    @RequestMapping("/admin/journey/update/{id}")
    public String update(@PathVariable long id, @Valid @ModelAttribute JourneyDto journeyDto) {
        journeyServiceInterface.update(id, journeyMapper.journeyDtoToJourney(journeyDto));
        return "redirect:/admin/journey";
    }

    @GetMapping("/admin/journey/company/{id}")
    public ModelAndView getJourneysForCompany(@PathVariable long id, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);
        Page<Journey> journeyPage = journeyServiceInterface.findPageSortingAfterDepartureTimeForCompanyId(currentPage, pageSize, id);
        ModelAndView modelAndView = new ModelAndView("journey-company");
        modelAndView.addObject("journeyPage", journeyPage);
        modelAndView.addObject("company", companyServiceInterface.findById(id));
        return modelAndView;
    }

    @GetMapping("/admin/journey/arrival/{id}")
    public ModelAndView getJourneysForArrivals(@PathVariable long id, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);
        Page<Journey> journeyPage = journeyServiceInterface.findPageSortingAfterDepartureTimeForArrivalRailway(currentPage, pageSize, id);
        ModelAndView modelAndView = new ModelAndView("journey-arrival");
        modelAndView.addObject("journeyPage", journeyPage);
        modelAndView.addObject("arrivalRailwayStationId", id);
        return modelAndView;
    }


    @GetMapping("/admin/journey/departure/{id}")
    public ModelAndView getJourneysForDepartures(@PathVariable long id, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);
        Page<Journey> journeyPage = journeyServiceInterface.findPageSortingAfterDepartureTimeForDepartureRailway(currentPage, pageSize, id);
        ModelAndView modelAndView = new ModelAndView("journey-departure");
        modelAndView.addObject("journeyPage", journeyPage);
        modelAndView.addObject("departureRailwayStationId", id);
        return modelAndView;
    }

    @GetMapping("/journey")
    public ModelAndView getJourneysForUsers(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);
        Page<Journey> journeyPage = journeyServiceInterface.findPageSortingAfterDepartureTimeForUsers(currentPage, pageSize);
        ModelAndView modelAndView = new ModelAndView("journey");
        modelAndView.addObject("journeyPage", journeyPage);
        return modelAndView;
    }


}
