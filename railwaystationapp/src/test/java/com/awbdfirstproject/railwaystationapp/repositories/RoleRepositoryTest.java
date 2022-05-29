package com.awbdfirstproject.railwaystationapp.repositories;

import com.awbdfirstproject.railwaystationapp.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void findByName() {
        Role role = new Role();
        role.setName("guest");
        roleRepository.save(role);
        Optional<Role> role1 = roleRepository.findByName("guest");
        assertEquals(role.getName(),role1.get().getName());
    }
}