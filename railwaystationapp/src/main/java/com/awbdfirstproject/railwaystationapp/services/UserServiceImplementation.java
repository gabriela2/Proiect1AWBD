package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.exception.*;
import com.awbdfirstproject.railwaystationapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserServiceImplementation implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(username));
        log.info("User " + user.getUsername() + " was found.");
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email));
        log.info("User " + user.getEmail() + " was found.");
        return user;
    }


    @Override
    public User save(User user) {
        log.info("User " + user.getUsername() + " was saved");
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        log.info("Verify if in db already exists a user with the username " + username);
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        log.info("Verify if in db already exists a user with the email " + email);
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void patchBalance(long userId, double balance) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId"));
        log.info("User " + user.getUsername() + " was found in db.");
        if (user.getBalance() + balance >= 0) {
            user.setBalance(user.getBalance() + balance);
            userRepository.save(user);
            log.info("The balance for " + user.getUsername() + " was updated.");
        } else {
            throw new BalanceUpdateException();
        }
    }

    @Override
    public User updatePersonalInfo(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new ResourceNotFoundException(user.getEmail()));
        log.info("User " + user.getUsername() + " was found in db.");
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setUserType(user.getUserType());
        existingUser.setPassword(user.getPassword());
        log.info("The personal informations for " + user.getUsername() + " were updated.");
        return userRepository.save(existingUser);

    }


}
