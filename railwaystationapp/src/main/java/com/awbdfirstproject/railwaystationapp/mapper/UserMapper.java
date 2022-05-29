package com.awbdfirstproject.railwaystationapp.mapper;

import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signUpDtoToUser(SignUpDto signUpDto) {
        return new User(signUpDto.getUsername(), signUpDto.getEmail(), passwordEncoder.encode(signUpDto.getPassword()), signUpDto.getFirstname(), signUpDto.getLastname(), signUpDto.getUserType());
    }

    public SignUpDto userToSignUpDto(User user) {
        return new SignUpDto(user.getFirstname(), user.getLastname(), user.getUsername(), user.getEmail(), user.getPassword(), user.getUserType());
    }
}
