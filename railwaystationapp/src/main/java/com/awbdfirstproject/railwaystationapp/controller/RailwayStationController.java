package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.dto.RailwayStationDto;
import com.awbdfirstproject.railwaystationapp.mapper.RailwayStationMapper;
import com.awbdfirstproject.railwaystationapp.services.RailwayStationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class RailwayStationController {
    @Autowired
    RailwayStationServiceInterface railwayStationServiceInterface;
    @Autowired
    RailwayStationMapper railwayStationMapper;

    @GetMapping("/railway-station")
    public ModelAndView railwayStation() {
        ModelAndView modelAndView = new ModelAndView("railway-station");
        List<RailwayStationDto> railwayStationDtoList = railwayStationServiceInterface.findAll().stream().map(railwayStation -> railwayStationMapper.railwayStationToRailwayStationDto(railwayStation)).collect(Collectors.toList());
        modelAndView.addObject("railwaystations", railwayStationDtoList);
        return modelAndView;
    }

    @RequestMapping("/railway-station/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        railwayStationServiceInterface.deleteById(id);
        return "redirect:/admin/railway-station";
    }

    @GetMapping("/railway-station/railway-station-form")
    public ModelAndView railwayStationForm() {
        ModelAndView modelAndView = new ModelAndView("railway-station-form");
        modelAndView.addObject("railwaystation", new RailwayStationDto());
        return modelAndView;
    }

    @PostMapping("/railway-station/railway-station-form")
    public String register(@Valid @ModelAttribute RailwayStationDto railwayStationDto) {
        railwayStationServiceInterface.save(railwayStationMapper.railwayStationDtoToRailwaystation(railwayStationDto));
        return "redirect:/admin/railway-station";
    }

    @GetMapping("/railway-station/update/{id}")
    public ModelAndView railwayStationUpdate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("railway-station-update");
        modelAndView.addObject("railwaystation", railwayStationMapper.railwayStationToRailwayStationDto(railwayStationServiceInterface.findById(id)));
        return modelAndView;
    }

    @RequestMapping("/railway-station/update/{id}")
    public String updateById(@PathVariable Long id, @Valid @ModelAttribute RailwayStationDto railwayStationDto) {
        railwayStationServiceInterface.update(id, railwayStationMapper.railwayStationDtoToRailwaystation(railwayStationDto));
        return "redirect:/admin/railway-station";
    }


}
