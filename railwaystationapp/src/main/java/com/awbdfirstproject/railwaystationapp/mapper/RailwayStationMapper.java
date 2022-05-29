package com.awbdfirstproject.railwaystationapp.mapper;

import com.awbdfirstproject.railwaystationapp.domain.Address;
import com.awbdfirstproject.railwaystationapp.domain.RailwayStation;
import com.awbdfirstproject.railwaystationapp.dto.RailwayStationDto;
import org.springframework.stereotype.Component;

@Component
public class RailwayStationMapper {
    public RailwayStationDto railwayStationToRailwayStationDto(RailwayStation railwayStation) {
        return new RailwayStationDto(railwayStation.getId(), railwayStation.getName(), railwayStation.getRailwayType(), railwayStation.getAddress().getNumber(),
                railwayStation.getAddress().getStreet(), railwayStation.getAddress().getCity(), railwayStation.getAddress().getDistrict(), railwayStation.getAddress().getZipcode());
    }

    public RailwayStation railwayStationDtoToRailwaystation(RailwayStationDto railwayStationDto) {
        Address address = new Address(railwayStationDto.getNumber(), railwayStationDto.getStreet(), railwayStationDto.getCity(), railwayStationDto.getDistrict(), railwayStationDto.getZipcode());
        return new RailwayStation(railwayStationDto.getName(), railwayStationDto.getRailwayType(), address);
    }
}
