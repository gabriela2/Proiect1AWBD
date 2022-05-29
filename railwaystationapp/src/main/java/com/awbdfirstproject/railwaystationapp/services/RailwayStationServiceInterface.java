package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.RailwayStation;

import java.util.List;

public interface RailwayStationServiceInterface {
    List<RailwayStation> findAll();

    void deleteById(Long id);

    RailwayStation save(RailwayStation railwayStationDtoToRailwaystation);

    RailwayStation findById(Long id);

    void update(Long id, RailwayStation newRailwayStationDto);

    RailwayStation findByName(String name);

    List<String> findAllByName();
}
