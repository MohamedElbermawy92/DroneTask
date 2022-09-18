package com.musala.drone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "Drone")
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @Column(length = 100,name="serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "battery_capacity",precision = 3, scale = 1)
    private BigDecimal batteryCapacity;

    @Column(name = "weight_limit")
    private double weightLimit;

    @Column(name = "state")
    private String state;
}
