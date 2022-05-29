package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.User;

public interface UserServiceInterface {
    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void patchBalance(long userId, double balance);

    User updatePersonalInfo(User user);

}
