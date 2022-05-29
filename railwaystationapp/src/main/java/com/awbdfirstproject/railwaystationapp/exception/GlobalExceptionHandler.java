package com.awbdfirstproject.railwaystationapp.exception;

import com.awbdfirstproject.railwaystationapp.dto.SignUpDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UsernameAlreadyExistsException.class, EmailAlreadyExistsException.class})
    public ModelAndView handlerUsernameOrEmailAlreadyExistsException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("signUpDto", new SignUpDto());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ResourceCannotBeDeletedException.class, ResourceAlreadyExistsException.class, InsufficientFundsException.class, ResourceCannotBeUpdatedException.class,
            ResourceNotFoundException.class, ResourseCannotBeSavedException.class, BalanceUpdateException.class})
    public ModelAndView handlerResourceCannotBeDeletedException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("resource-exception");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

}
