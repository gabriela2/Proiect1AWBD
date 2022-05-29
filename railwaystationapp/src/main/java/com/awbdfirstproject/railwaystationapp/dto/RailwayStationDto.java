package com.awbdfirstproject.railwaystationapp.dto;

import com.awbdfirstproject.railwaystationapp.domain.RailwayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RailwayStationDto {
    private long id;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotNull
    private RailwayType railwayType;
    @Size(max = 10)
    @NotBlank
    private String number;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    @Size(max = 30)
    private String district;
    @NotBlank
    @Size(max = 10)
    private String zipcode;
}
