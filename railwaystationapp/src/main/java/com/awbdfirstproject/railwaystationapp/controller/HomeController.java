package com.awbdfirstproject.railwaystationapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {


    @RequestMapping({"", "/"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }


}
