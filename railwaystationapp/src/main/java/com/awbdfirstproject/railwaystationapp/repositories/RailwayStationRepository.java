package com.awbdfirstproject.railwaystationapp.repositories;

import com.awbdfirstproject.railwaystationapp.domain.RailwayStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RailwayStationRepository extends JpaRepository<RailwayStation, Long> {
    Optional<RailwayStation> getByName(String name);

    Optional<RailwayStation> findByName(String name);

    @Query(nativeQuery = true, value = "select name from railway_station")
    List<String> findAllNames();
}