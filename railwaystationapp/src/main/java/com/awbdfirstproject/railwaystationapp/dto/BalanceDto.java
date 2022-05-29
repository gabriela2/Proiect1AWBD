package com.awbdfirstproject.railwaystationapp.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class BalanceDto {
    @NotNull
    @Min(0)
    private Double balance;
}
