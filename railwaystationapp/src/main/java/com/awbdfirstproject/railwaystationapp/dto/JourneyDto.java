package com.awbdfirstproject.railwaystationapp.dto;

import com.awbdfirstproject.railwaystationapp.domain.JourneyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class JourneyDto {
    private long id;
    @NotNull
    @Min(0)
    private int distance;
    @NotNull
    private Timestamp departureTime;
    @NotNull
    private Timestamp arrivalTime;
    private int minuteLate;
    @NotNull
    @Min(0)
    private double ticketPrice;
    @NotNull
    private JourneyStatus journeyStatus;
    @NotNull
    private String trainCode;
    @NotNull
    private String companyName;
    @NotNull
    private String departureRailway;
    @NotNull
    private String arrivalRailway;

    public JourneyDto(long id, int distance, Timestamp departureTime, Timestamp arrivalTime, int minuteLate, double ticketPrice, JourneyStatus journeyStatus, String trainCode, String companyName, String departureRailway, String arrivalRailway) {
        this.id = id;
        this.distance = distance;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.minuteLate = minuteLate;
        this.ticketPrice = ticketPrice;
        this.journeyStatus = journeyStatus;
        this.trainCode = trainCode;
        this.companyName = companyName;
        this.departureRailway = departureRailway;
        this.arrivalRailway = arrivalRailway;
    }
}
