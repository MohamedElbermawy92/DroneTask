package com.musala.drone.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneRequest {

    private String serialNumber;
    private String model;
    private double weightLimit;
    private BigDecimal battery;
    private String state;
}
