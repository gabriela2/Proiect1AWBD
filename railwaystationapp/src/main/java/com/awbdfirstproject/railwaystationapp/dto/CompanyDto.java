package com.awbdfirstproject.railwaystationapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 15)
    private String identificationNumber;
}
