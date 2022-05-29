package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.domain.Train;
import com.awbdfirstproject.railwaystationapp.services.TrainServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TrainController {
    @Autowired
    TrainServiceInterface trainServiceInterface;

    @GetMapping(path = "/train")
    public ModelAndView company() {
        ModelAndView modelAndView = new ModelAndView("train");
        List<Train> trains = trainServiceInterface.findAll();
        modelAndView.addObject("trains", trains);
        return modelAndView;
    }


    @GetMapping(path = "/train/train-form")
    public ModelAndView trainForm() {
        ModelAndView modelAndView = new ModelAndView("train-form");
        modelAndView.addObject("train", new Train());
        return modelAndView;
    }

    @PostMapping("/train/train-form")
    public String register(@Valid @ModelAttribute Train train) {
        trainServiceInterface.save(train);
        return "redirect:/admin/train";
    }

    @RequestMapping("train/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        trainServiceInterface.deleteById(id);
        return "redirect:/admin/train";
    }

    @GetMapping("/train/update/{id}")
    public String trainUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("train", trainServiceInterface.findById(id));
        return "train-update";
    }

    @RequestMapping("/train/update/{id}")
    public String updateById(@PathVariable Long id, @Valid @ModelAttribute Train train) {
        trainServiceInterface.update(id, train);
        return "redirect:/admin/train";
    }


}
