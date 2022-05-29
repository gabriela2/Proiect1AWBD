package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.domain.Role;
import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.dto.BalanceDto;
import com.awbdfirstproject.railwaystationapp.dto.SignUpDto;
import com.awbdfirstproject.railwaystationapp.exception.EmailAlreadyExistsException;
import com.awbdfirstproject.railwaystationapp.exception.UsernameAlreadyExistsException;
import com.awbdfirstproject.railwaystationapp.mapper.UserMapper;
import com.awbdfirstproject.railwaystationapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    TicketServiceInterface ticketServiceInterface;
    private UserServiceInterface userServiceInterface;
    private RoleServiceInterface roleServiceInterface;
    private UserMapper userMapper;

    public UserController(UserServiceInterface userServiceInterface, RoleServiceInterface roleServiceInterface, UserMapper userMapper) {
        this.userServiceInterface = userServiceInterface;
        this.roleServiceInterface = roleServiceInterface;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/login-error")
    public ModelAndView loginError() {
        ModelAndView modelAndView = new ModelAndView("login-error");
        return modelAndView;
    }


    @GetMapping(path = "/register")
    public String registerForm(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "register";
    }


    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute SignUpDto signUpDto, BindingResult bindingResult) {
        ModelAndView viewRegister = new ModelAndView("register");
        if (bindingResult.hasErrors()) {
            return viewRegister;
        }
        if (userServiceInterface.existsByUsername(signUpDto.getUsername())) {
            throw new UsernameAlreadyExistsException(signUpDto.getUsername());
        }

        if (userServiceInterface.existsByEmail(signUpDto.getEmail())) {
            throw new EmailAlreadyExistsException(signUpDto.getEmail());
        }

        User user = userMapper.signUpDtoToUser(signUpDto);
        Role roles = roleServiceInterface.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(roles));

        userServiceInterface.save(user);
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping(path = "/profile")
    public ModelAndView profilePage(Principal principal) {
        User user = userServiceInterface.findByEmail(principal.getName());
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("tickes", ticketServiceInterface.findTicketsForUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping("/profile/edit-balance")
    public ModelAndView editBalance() {
        ModelAndView modelAndView = new ModelAndView("edit-balance");
        modelAndView.addObject("balanceDto", new BalanceDto());
        return modelAndView;
    }

    @RequestMapping("/profile/edit-balance")
    public ModelAndView patchBalance(Principal principal, @Valid @ModelAttribute BalanceDto balanceDto) {
        User user = userServiceInterface.findByEmail(principal.getName());
        userServiceInterface.patchBalance(user.getId(), balanceDto.getBalance());
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/profile/edit-profile")
    public ModelAndView getEditProfile(Principal principal) {
        User user = userServiceInterface.findByEmail(principal.getName());
        ModelAndView modelAndView = new ModelAndView("edit-profile");
        modelAndView.addObject("signUpDto", userMapper.userToSignUpDto(user));
        return modelAndView;
    }

    @RequestMapping("/profile/edit-profile")
    public ModelAndView putEditProfile(@Valid @ModelAttribute SignUpDto signUpDto) {
        User user = userServiceInterface.updatePersonalInfo(userMapper.signUpDtoToUser(signUpDto));
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }


}
