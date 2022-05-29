package com.awbdfirstproject.railwaystationapp.repositories;

import com.awbdfirstproject.railwaystationapp.domain.Journey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;


@Repository
public interface JourneyRepository extends PagingAndSortingRepository<Journey, Long> {
    Page<Journey> findAllByCompanyId(long companyId, Pageable pageable);

    @Query(value = "select * from journey where company_id=:company_id and departure_time=:departureTime and arrival_time=:arrivalTime and departure_railway_station_id=:departure_railway_station_id and arrival_railway_station_id=:arrival_railway_station_id and train_id=:train_id", nativeQuery = true)
    Optional<Journey> findJourney(long company_id, Timestamp departureTime, Timestamp arrivalTime, long train_id, long departure_railway_station_id, long arrival_railway_station_id);

    Page<Journey> findAllByDepartureRailwayStationId(long departureRailwayStationId, Pageable pageable);

    Page<Journey> findAllByArrivalRailwayStationId(long arrivalRailwayStationId, Pageable pageable);

    Page<Journey> findByDepartureTimeGreaterThan(Timestamp departureTime, Pageable pageable);


}
