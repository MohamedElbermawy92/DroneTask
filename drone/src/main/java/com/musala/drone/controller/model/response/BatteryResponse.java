package com.musala.drone.controller.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BatteryResponse {
     String serialNumber;
     String battery;
}
