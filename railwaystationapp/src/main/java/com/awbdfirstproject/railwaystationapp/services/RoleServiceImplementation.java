package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Role;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImplementation implements RoleServiceInterface {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException(name));
        log.info("Role " + role.getName() + " was found.");
        return role;
    }
}
