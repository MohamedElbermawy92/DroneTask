package com.musala.drone.controller.model.response;

import com.musala.drone.model.Drone;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AvailableDronesResponse {
     List<Drone> drones;
}
