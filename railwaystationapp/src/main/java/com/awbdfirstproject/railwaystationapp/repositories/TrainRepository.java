package com.awbdfirstproject.railwaystationapp.repositories;

import com.awbdfirstproject.railwaystationapp.domain.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByCode(String code);

    @Query(nativeQuery = true, value = "select code from train")
    List<String> findAllCodes();
}
