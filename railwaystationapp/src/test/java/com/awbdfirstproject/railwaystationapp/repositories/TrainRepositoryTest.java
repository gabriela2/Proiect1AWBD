package com.awbdfirstproject.railwaystationapp.repositories;

import com.awbdfirstproject.railwaystationapp.domain.FuelType;
import com.awbdfirstproject.railwaystationapp.domain.Train;
import com.awbdfirstproject.railwaystationapp.domain.TrainType;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class TrainRepositoryTest {
    @Autowired
    TrainRepository trainRepository;

    @Test
    @Order(1)
    void findByCode() {
        Train train = new Train();
        train.setId(1);
        train.setNumberOfSeats(123);
        train.setFuelType(FuelType.DIESEL);
        train.setTrainType(TrainType.REGIO);
        train.setCode("123");

        trainRepository.save(train);
        Optional<Train> train1 = trainRepository.findByCode("123");
        assertEquals(train.getCode(),train1.get().getCode());
    }

    @Test
    @Order(2)
    void findAllCodes() {
        Train train = new Train();
        train.setId(2);
        train.setCode("456");
        train.setNumberOfSeats(123);
        train.setFuelType(FuelType.DIESEL);
        train.setTrainType(TrainType.REGIO);
        trainRepository.save(train);
        List<String> codes= new ArrayList<>();
        codes.add("123");
        codes.add("456");
        List<String> codesfromDb = trainRepository.findAllCodes();
        assertEquals(codes,codesfromDb);
    }
}