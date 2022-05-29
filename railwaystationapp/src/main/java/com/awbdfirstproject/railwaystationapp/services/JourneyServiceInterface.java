package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Journey;
import org.springframework.data.domain.Page;

public interface JourneyServiceInterface {

    Page<Journey> findPageSortingAfterDepartureTimeForCompanyId(int currentPage, int limitPage, long companyId);

    Page<Journey> findPageSortingAfterDepartureTimeForDepartureRailway(int currentPage, int limitPage, long departureRailwayStationId);

    Page<Journey> findPageSortingAfterDepartureTimeForArrivalRailway(int currentPage, int limitPage, long arrivalRailwayStationId);

    Page<Journey> findPageSortingAfterDepartureTimeForAdmin(int currentPage, int limitPage);

    Page<Journey> findPageSortingAfterDepartureTimeForUsers(int currentPage, int limitPage);

    void deleteById(long id);

    void update(Long id, Journey journey);

    Journey save(Journey journey);


    Journey findById(long id);
}
