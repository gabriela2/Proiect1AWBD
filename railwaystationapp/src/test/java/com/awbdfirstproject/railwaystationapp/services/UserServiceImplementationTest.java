package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.domain.UserType;
import com.awbdfirstproject.railwaystationapp.exception.BalanceUpdateException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImplementation userServiceImplementation;

    @Test
    void findByUsernameNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->userServiceImplementation.findByUsername(anyString()));
        assertEquals("Resursa  nu poate fi gasita!", exception.getMessage());

    }

    @Test
    void findByUsernameFound() {
        User user = new User();
        user.setUsername("username");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        User result= userServiceImplementation.findByUsername(anyString());
        assertEquals(user,result);
    }

    @Test
    void findByEmailNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->userServiceImplementation.findByEmail(anyString()));
        assertEquals("Resursa  nu poate fi gasita!", exception.getMessage());

    }

    @Test
    void findByEmailFound() {
        User user = new User();
        user.setEmail("username");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        User result= userServiceImplementation.findByEmail(anyString());
        assertEquals(user,result);
    }



    @Test
    void save() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        User result = userServiceImplementation.save(user);
        assertEquals(user,result);
    }

    @Test
    void existsByUsername() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        assertFalse(userServiceImplementation.existsByUsername("user"));
    }

    @Test
    void existsByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertFalse(userServiceImplementation.existsByEmail("user"));
    }

   @Test
    void updatePersonalInfoWhenInfoNotFound(){
        User user = new User("ana","ana","ana","ana","ana", UserType.SOCIAL);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->userServiceImplementation.updatePersonalInfo(user));
        assertEquals("Resursa ana nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void updatePersonalInfoWhenInfoFound(){
        User user = new User("ana","ana","ana","ana","ana", UserType.SOCIAL);
        User user1 = new User("ana1","ana1","ana1","ana1","ana1", UserType.SOCIAL);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        user.setFirstname(user1.getFirstname());
        user.setLastname(user1.getLastname());
        user.setUserType(user1.getUserType());
        user.setPassword(user1.getPassword());
        when(userRepository.save(user)).thenReturn(user);
        User result = userServiceImplementation.updatePersonalInfo(user1);
        assertEquals(user,result);
    }

    @Test
    void updateBalanceWhenUserNotFound(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->userServiceImplementation.patchBalance(anyLong(),1.2));
        assertEquals("Resursa userId nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void updateBalanceWhenUserFoundBalanceNegative(){
        User user = new User("ana","ana","ana","ana","ana", UserType.SOCIAL);
        user.setBalance(0.0);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        BalanceUpdateException exception = assertThrows(BalanceUpdateException.class,()->userServiceImplementation.patchBalance(anyLong(),-1.2));
        assertEquals("Balanta contului nu poate fi mai mica decat 0!", exception.getMessage());
    }

    @Test
    void updateBalanceWhenUserFoundBalancePositive(){
        User user = new User("ana","ana","ana","ana","ana", UserType.SOCIAL);
        user.setBalance(0.0);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        user.setBalance(user.getBalance()+1.2);
        when(userRepository.save(user)).thenReturn(user);
        userServiceImplementation.patchBalance(anyLong(),1.2);
        verify(userRepository).save(user);
    }

}