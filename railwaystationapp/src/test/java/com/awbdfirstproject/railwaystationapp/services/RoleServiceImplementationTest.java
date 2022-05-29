package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Role;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RoleServiceImplementationTest {

    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    RoleServiceImplementation roleServiceImplementation;

    @Test
    void findByNameNotFound() {
        when(roleRepository.findByName(anyString())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->roleServiceImplementation.findByName(anyString()));
        assertEquals("Resursa  nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void findByNameFound() {
        Role role = new Role();
        role.setName("ADMIN");
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));
        Role result= roleServiceImplementation.findByName(anyString());
        assertEquals(role,result);
    }
}