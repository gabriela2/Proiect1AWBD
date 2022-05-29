package com.awbdfirstproject.railwaystationapp.mapper;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.domain.RailwayStation;
import com.awbdfirstproject.railwaystationapp.domain.Train;
import com.awbdfirstproject.railwaystationapp.dto.JourneyDto;
import com.awbdfirstproject.railwaystationapp.services.CompanyServiceInterface;
import com.awbdfirstproject.railwaystationapp.services.RailwayStationServiceInterface;
import com.awbdfirstproject.railwaystationapp.services.TrainServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JourneyMapper {
    @Autowired
    private CompanyServiceInterface companyServiceInterface;
    @Autowired
    private TrainServiceInterface trainServiceInterface;
    @Autowired
    private RailwayStationServiceInterface railwayStationServiceInterface;

    public Journey journeyDtoToJourney(JourneyDto journeyDto) {
        Company company = companyServiceInterface.findByName(journeyDto.getCompanyName());
        Train train = trainServiceInterface.findByCode(journeyDto.getTrainCode());
        RailwayStation departure = railwayStationServiceInterface.findByName(journeyDto.getDepartureRailway());
        RailwayStation arrival = railwayStationServiceInterface.findByName(journeyDto.getArrivalRailway());

        return new Journey(journeyDto.getId(), journeyDto.getDistance(), journeyDto.getDepartureTime(), journeyDto.getArrivalTime(), journeyDto.getMinuteLate(), journeyDto.getTicketPrice(), journeyDto.getJourneyStatus(), train, departure, arrival, company);
    }

    public JourneyDto journeyToJourneyDto(Journey journey) {
        return new JourneyDto(journey.getId(), journey.getDistance(), journey.getDepartureTime(), journey.getArrivalTime(), journey.getMinuteLate(), journey.getTicketPrice(), journey.getJourneyStatus(), journey.getTrain().getCode(), journey.getCompany().getName(), journey.getDepartureRailwayStation().getName(), journey.getArrivalRailwayStation().getName());
    }
}
