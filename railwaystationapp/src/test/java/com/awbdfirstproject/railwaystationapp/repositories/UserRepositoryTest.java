package com.awbdfirstproject.railwaystationapp.repositories;

import com.awbdfirstproject.railwaystationapp.domain.User;
import com.awbdfirstproject.railwaystationapp.domain.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void findByUsernameOrEmail() {
        User user = new User("username","email@email.com","anaaremere","prenume","nume", UserType.NORMAL);
        userRepository.save(user);
        Optional<User> user1 = userRepository.findByUsernameOrEmail("username","email@email.com");
        assertFalse(user1.isEmpty());
    }

    @Test
    @Order(2)
    void findByUsername() {
        Optional<User> user1 = userRepository.findByUsername("username");
        assertFalse(user1.isEmpty());
    }

    @Test
    @Order(3)
    void findByEmail() {
        Optional<User> user1 = userRepository.findByEmail("email@email.com");
        assertFalse(user1.isEmpty());
    }

    @Test
    @Order(4)
    void existsByUsername() {
        Boolean user1 = userRepository.existsByUsername("username");
        assertTrue(user1);
    }

    @Test
    @Order(5)
    void existsByEmail() {
        Boolean user1 = userRepository.existsByEmail("email@email.com");
        assertTrue(user1);
    }
}